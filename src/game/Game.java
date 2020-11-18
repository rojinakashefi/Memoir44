package game;

import equipment.Artillery;
import equipment.Infantry;
import equipment.Tank;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player axisPlayer;
    private Player alliedPlayer;

    public Game() {
    }

    public String changeTurn(String turn) {
        if (turn.isEmpty())
            return "Allied";
        return turn.equalsIgnoreCase("Allied") ? "Axis" : "Allied";
    }

    public void start() {
        String turn = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            turn = changeTurn(turn);
            System.out.println("TURN: " + getCurrentPalyer(turn).getName());
            System.out.println("Select Cards To Use: " + getCurrentPalyer(turn).getName());
            getCurrentPalyer(turn).printAvailableCards();
            int selectedCard = scanner.nextInt() - 1;
            Card card = getCurrentPalyer(turn).remove(selectedCard);
            GameBoard.playedCards.add(card);
            move(getCurrentPalyer(turn), card, turn);
            attack(getCurrentPalyer(turn), card, turn);
            reassignCardsToCurrentPlayer(turn);
            if (GameBoard.board.get(2).get(0).getPlayerName() == turn || GameBoard.board.get(8).get(11).getPlayerName() == turn) {
                getCurrentPalyer(turn).setScore(getCurrentPalyer(turn).getScore() + 1);
            }
            if (getCurrentPalyer(turn).getScore() == 6) {
                System.out.println(getAlliedPlayer().getName() + "is WINNER!");
                break;
            }
        }
    }

    private void reassignCardsToCurrentPlayer(String turn) {
        if (GameBoard.cards.size() == 0) {
            for (int i = 0; i < GameBoard.playedCards.size(); i++) {
                GameBoard.cards.add(GameBoard.playedCards.get(i));
            }
        }
        GameBoard.playedCards = new ArrayList<>();
        int randomCardIndex = Dice.roll(GameBoard.cards.size());
        getCurrentPalyer(turn).getCards().add(GameBoard.cards.get(randomCardIndex));
    }

    private void attack(Player currentPalyer, Card card, String turn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Attack info: 'row col' of your units:sample 1 3");
        System.out.println("or, enter -1 -1 for exit");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        if (row == -1 && col == -1)
            return;

        System.out.println("Attack goal: 'row col' of oponent units for atack:sample 5 4");
        int goalRow = scanner.nextInt();
        int goalCol = scanner.nextInt();
        int rowDistance = Math.abs(row - goalRow);
        int colDistance = Math.abs(col - goalCol);
        if (rowDistance > 6 && colDistance > 6) {
            System.out.println("Illegal distance for attack");
            return;
        }
        GameUnit goalGameUnit = GameBoard.board.get(goalRow).get(goalCol);
        if (goalGameUnit.getPlayerName() == turn) {
            System.out.println("Error(not allowed): this is your units for atatck");
            return;
        }
        if (goalGameUnit.getEquipmentGroup().getEquipment().size() == 0) {
            System.out.println("Illegal goal for attack");
            return;
        }

        if (goalGameUnit.getEquipmentGroup().getEquipment().get(0) instanceof Tank) {
            if (rowDistance <= 3 || colDistance <= 3) {
                for (int i = 0; i < 3; i++) {
                    int dice = Dice.roll();
                    if (dice == 2) {
                        GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().remove(0);
                        score(turn, goalRow, goalCol);
                        break;
                    }
                }
            }
        } else if (goalGameUnit.getEquipmentGroup().getEquipment().get(0) instanceof Infantry) {
            if (rowDistance == 3 || colDistance == 3) {
                for (int i = 0; i < 1; i++) {
                    int dice = Dice.roll();
                    if (dice == 1 || dice == 6) {
                        GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().remove(0);
                        score(turn, goalRow, goalCol);
                        break;
                    }
                }
            } else if (rowDistance == 2 || colDistance == 2) {
                for (int i = 0; i < 2; i++) {
                    int dice = Dice.roll();
                    if (dice == 1 || dice == 6 || dice == 5) {
                        GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().remove(0);
                        score(turn, goalRow, goalCol);
                        break;
                    }
                }
            } else if (rowDistance == 1 || colDistance == 1) {
                for (int i = 0; i < 3; i++) {
                    int dice = Dice.roll();
                    if (dice == 1 || dice == 6 || dice == 5) {
                        GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().remove(0);
                        score(turn, goalRow, goalCol);
                        break;
                    }
                }
            }
        } else if (goalGameUnit.getEquipmentGroup().getEquipment().get(0) instanceof Artillery) {
            if (rowDistance == 6 || colDistance == 6 || rowDistance == 5 || colDistance == 5) {
                for (int i = 0; i < 1; i++) {
                    int dice = Dice.roll();
                    if (dice == 5) {
                        GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().remove(0);
                        score(turn, goalRow, goalCol);
                        break;
                    }
                }
            } else if (rowDistance == 3 || colDistance == 3 || rowDistance == 4 || colDistance == 4) {
                for (int i = 0; i < 4; i++) {
                    int dice = Dice.roll();
                    if (dice == 5) {
                        GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().remove(0);
                        score(turn, goalRow, goalCol);
                        break;
                    }
                }
            } else if (rowDistance == 1 || colDistance == 1 || rowDistance == 2 || colDistance == 2) {
                for (int i = 0; i < 3; i++) {
                    int dice = Dice.roll();
                    if (dice == 5) {
                        GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().remove(0);
                        score(turn, goalRow, goalCol);
                        break;
                    }
                }
            }
        }


    }

    private void score(String turn, int goalRow, int goalCol) {
        if (GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().size() == 0) {
            getCurrentPalyer(turn).setScore(getCurrentPalyer(turn).getScore() + 1);
        }
    }

    private void move(Player currentPalyer, Card card, String turn) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        while (true) {
            System.out.println("enter 'row col' of your units-sample:1 3");
            System.out.println("or, enter -1 -1 for exit");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row == -1 && col == -1)
                break;

            System.out.println("please select one of movements for your selected units");
            System.out.println("1-U 2-D 3-UR 4-UL 5-DR 6-DL");
            int movement = scanner.nextInt();
            boolean isMovementSuccessful = movement(currentPalyer, row, col, movement, card, turn);
            if (isMovementSuccessful) {
                counter++;
            }
            if (!checkConstraint(currentPalyer, counter, card))
                break;
        }
    }

    private boolean movement(Player currentPalyer, int row, int col, int movement, Card card, String turn) {
        row--;
        col--;
        if (row > 8 || col > 12 || row < 0 || col < 0) {
            System.out.println("Error: out of range is selected");
            return false;
        }
        GameUnit gameUnit = GameBoard.board.get(row).get(col);
        if (gameUnit.getPlayerName() != turn) {
            System.out.println("Error: please select your units");
            return false;
        }
        //check equiepmentgs with cards

        //check card natural units

        //check empty equipment
        int movementRow = movementRow(row, movement);
        int movementCol = movementCol(col, movement);
        if (movementRow == -1 || movementCol == -1) {
            System.out.println("Not Allowed-out of range");
            return false;
        }
        if (GameBoard.board.get(movementRow).get(movementCol).getEquipmentGroup().getEquipment().size() > 0) {
            System.out.println("Not Allowed-Destination is not empty");
            return false;
        }
        GameBoard.board.get(movementRow).get(movementCol).setEquipmentGroup(gameUnit.getEquipmentGroup());
        GameBoard.board.get(movementRow).get(movementCol).setPlayerName(gameUnit.getPlayerName());
        GameBoard.board.get(row).get(col).setEquipmentGroup(null);
        GameBoard.board.get(row).get(col).setPlayerName(null);
        GameBoard.print();
        return true;
    }

    private int movementCol(int col, int movement) {
        int y = col;
        switch (movement) {
            case 3:
            case 5:
                y++;
                break;
            case 4:
            case 6:
                y--;
                break;
        }
        if (y < 0 || y > 12)
            return -1;
        return y;
    }

    private int movementRow(int row, int movement) {
        int x = row;
        switch (movement) {
            case 1:
            case 3:
            case 4:
                x--;
                break;
            case 2:
            case 5:
            case 6:
                x++;
                break;
        }
        if (x < 0 || x > 8)
            return -1;
        return x;
    }

    private boolean checkConstraint(Player player, int counter, Card card) {
        if (card.getCardType() == CardType.SingleGroupCommand && counter == 1) {
            return false;
        }
        if (card.getCardType() == CardType.TwoGroupCommand && counter == 2) {
            return false;
        }
        if (card.getCardType() == CardType.ThreeGroupCommand && counter == 3) {
            return false;
        }
        if (card.getCardType() == CardType.ThreeUnitFromOneEquipmentCommand && counter == 1) {
            return false;
        }
        return true;
    }

    private Player getCurrentPalyer(String turn) {
        return turn.equalsIgnoreCase("Allied") ? alliedPlayer : axisPlayer;
    }

    public void gameRound() {
        //Axis

        //Allied
    }

    public void assignEquipmentToPlayers() {
        this.alliedPlayer.setAlliedEquipments();
        this.axisPlayer.setAxisEquipments();
    }

    public void getPlayersInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Axis Player name:");
        String axisPlayerName = scanner.nextLine();
        this.axisPlayer = new Player(axisPlayerName);
        System.out.println("Please enter Allied Player name:");
        String alliedPlayerName = scanner.nextLine();
        this.alliedPlayer = new Player(alliedPlayerName);
    }

    public void printPlayers() {
        System.out.println("Axis Player is: " + this.axisPlayer.getName());
        System.out.println("Allied Player is: " + this.alliedPlayer.getName());
    }

    public Player getAxisPlayer() {
        return axisPlayer;
    }

    public void setAxisPlayer(Player axisPlayer) {
        this.axisPlayer = axisPlayer;
    }

    public Player getAlliedPlayer() {
        return alliedPlayer;
    }

    public void setAlliedPlayer(Player alliedPlayer) {
        this.alliedPlayer = alliedPlayer;
    }

    public void initialize() {
        this.assignEquipmentToPlayers();
        GameBoard.initializeBoard();
        GameBoard.initializeEquipments(axisPlayer, alliedPlayer);
        GameBoard.initializeCards(axisPlayer, alliedPlayer);
    }
}
