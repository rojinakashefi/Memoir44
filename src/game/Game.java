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

    /**
     * set whose player turn is
     * @param turn as knowing whose player turn is
     * @return the player turn
     */
    public String changeTurn(String turn) {
        if (turn.isEmpty())
            return "Allied";
        return turn.equalsIgnoreCase("Allied") ? "Axis" : "Allied";
    }

    /**
     * start of game starts with choosing a player turn(it can be allied and axsis)
     * show available cards for player
     * selecting that card and removing from player available cards and add it to playedcard of game
     */
    public void start() {
        String turn = "";
        Scanner scanner = new Scanner(System.in);
        //continue till we find winner
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
            //if the player which is in a game is in special game units we will increase thier score
            if (GameBoard.board.get(2).get(0).getPlayerName() == turn || GameBoard.board.get(8).get(11).getPlayerName() == turn) {
                getCurrentPalyer(turn).setScore(getCurrentPalyer(turn).getScore() + 1);
            }
            //check if we found a player or not
            if (getCurrentPalyer(turn).getScore() == 6) {
                System.out.println(getAlliedPlayer().getName() + "is WINNER!");
                break;
            }
        }
    }

    /**
     * giving new card to player because the player has used thier cards
     * @param turn as which player turn is
     */
    private void reassignCardsToCurrentPlayer(String turn) {
        //if cards has been finished we use played cards and add them to game cards
        if (GameBoard.cards.size() == 0) {
            for (int i = 0; i < GameBoard.playedCards.size(); i++) {
                GameBoard.cards.add(GameBoard.playedCards.get(i));
            }
        }
        //newing played of cards
        GameBoard.playedCards = new ArrayList<>();
        //the dice must choose a card by random based on size of cards we have
        int randomCardIndex = Dice.roll(GameBoard.cards.size());
        //adding the card the dice choose randomly to player cards
        getCurrentPalyer(turn).getCards().add(GameBoard.cards.get(randomCardIndex));
    }

    /**
     * we attack to the place based on user choose there are multiple rules for attacking
     * @param currentPlayer as player which is playing
     * @param card as card the player choosen to play game with
     * @param turn as turn of which group is
     */
    private void attack(Player currentPlayer, Card card, String turn) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Attack info: 'row col' of your units:sample 1 3");
        System.out.println("or, enter -1 -1 for exit");
        //setting place of your unites
        int row = scanner.nextInt()-1;
        int col = scanner.nextInt()-1;

        if (row == -1 && col == -1)
            return;

        System.out.println("Attack goal: 'row col' of oponent units for atack:sample 5 4");
        //setting goal destination we want to attack
        int goalRow = scanner.nextInt()-1;
        int goalCol = scanner.nextInt()-1;
        int rowDistance = Math.abs(row - goalRow);
        int colDistance = Math.abs(col - goalCol);
        //check the distance of current place and destination place
        if (rowDistance > 6 && colDistance > 6) {
            System.out.println("Illegal distance for attack");
            return;
        }
        //check goalgameunit
        GameUnit goalGameUnit = GameBoard.board.get(goalRow).get(goalCol);
        //checking if the unit destination is allowed or not
        if (goalGameUnit.getPlayerName() == turn) {
            System.out.println("Error(not allowed): this is your units for attack");
            return;
        }
        //???
        if (goalGameUnit.getEquipmentGroup().getEquipment().size() == 0) {
            System.out.println("Illegal goal for attack");
            return;
        }
        //if there is tank we should check the distance if must be smaller than 3 and dice must be 2
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
        }
        //if it is infantry there multiple rules for dice and distance
        else if (goalGameUnit.getEquipmentGroup().getEquipment().get(0) instanceof Infantry) {
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
        }
        //if it is Artillery there are multiple rules for dice and distance
        else if (goalGameUnit.getEquipmentGroup().getEquipment().get(0) instanceof Artillery) {
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
    /**
     * check if we move our equipment to destination how will our score move
     * @param turn as player turn
     * @param goalRow as row of goal destination
     * @param goalCol as col of destination
     */
    private void score(String turn, int goalRow, int goalCol) {
        //if player in opposite side equipment has been zero we will increased the player score
        if (GameBoard.board.get(goalRow).get(goalCol).getEquipmentGroup().getEquipment().size() == 0) {
            getCurrentPalyer(turn).setScore(getCurrentPalyer(turn).getScore() + 1);
        }
    }

    /**
     * first we ask users to select where they wanna move
     * then should choose how they wanna move there
     * then we check if they can move there or not
     * @param currentPlayer as the player which is playing
     * @param card as the card he/she has choosen to play with
     * @param turn as whose turn is
     */
    private void move(Player currentPlayer, Card card, String turn) {
        Scanner scanner = new Scanner(System.in);
        //use counter to see how many movement does a player does based on the card the player have
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
            boolean isMovementSuccessful = movement(currentPlayer, row, col, movement, card, turn);
            if (isMovementSuccessful) {
                counter++;
            }
            //check are allowed to continuing moving or not
            if (!checkConstraint(currentPlayer, counter, card))
                break;
        }
    }

    /**
     * check if we can move and element or not
     * @param currentPlayer as the player which is playing
     * @param row as row the player selected which wants to move there
     * @param col as col the player selected which wants to move there
     * @param movement as how the player choosed to go the destination
     * @param card as card of player
     * @param turn as whose turn is
     * @return if the destination is available for moving or not
     */
    private boolean movement(Player currentPlayer, int row, int col, int movement, Card card, String turn) {
        row--;
        col--;
        //the row and col we choosed must be in boardgame
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

    /**
     *
     * @param col as the col we want to have move in
     * @param movement as movement we want to do
     * @return the new y
     */
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

    /**
     *
     * @param row as row we want to change
     * @param movement as movement we want to do
     * @return new x
     */
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

    /**
     * check if based on the card player has choosen and the movement we have already done, are we allowed to continue moving or not
     * @param player as the player which is playing
     * @param counter as how many moves does the player has done
     * @param card as card type of player has choosen
     * @return if we can move more
     */
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

    /**
     * get player who is playing
     * @param turn as whose group turn is
     * @return player info
     */
    private Player getCurrentPalyer(String turn) {
        return turn.equalsIgnoreCase("Allied") ? alliedPlayer : axisPlayer;
    }

    /**
     * setting equipment to player
     */
    public void assignEquipmentToPlayers() {
        this.alliedPlayer.setAlliedEquipments();
        this.axisPlayer.setAxisEquipments();
    }

    /**
     * getting player information
     */
    public void getPlayersInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Axis Player name:");
        String axisPlayerName = scanner.nextLine();
        this.axisPlayer = new Player(axisPlayerName);
        System.out.println("Please enter Allied Player name:");
        String alliedPlayerName = scanner.nextLine();
        this.alliedPlayer = new Player(alliedPlayerName);
    }

    /**
     * print what is each player group
     */
    public void printPlayers() {
        System.out.println("Axis Player is: " + this.axisPlayer.getName());
        System.out.println("Allied Player is: " + this.alliedPlayer.getName());
    }

    /**
     * getting axis player
     * @return axis player of game
     */
    public Player getAxisPlayer() {
        return axisPlayer;
    }

    /**
     * setting axis player
     * @param axisPlayer as axis player of game
     */
    public void setAxisPlayer(Player axisPlayer) {
        this.axisPlayer = axisPlayer;
    }

    /**
     * getting allied player
     * @return allied player
     */
    public Player getAlliedPlayer() {
        return alliedPlayer;
    }

    /**
     * setting allied player
     * @param alliedPlayer as allied player
     */
    public void setAlliedPlayer(Player alliedPlayer) {
        this.alliedPlayer = alliedPlayer;
    }

    /**
     * for initializing game first we assign equipments to player
     */
    public void initialize() {
        this.assignEquipmentToPlayers();
        GameBoard.initializeBoard();
        GameBoard.initializeEquipments(axisPlayer, alliedPlayer);
        GameBoard.initializeCards(axisPlayer, alliedPlayer);
    }
}
