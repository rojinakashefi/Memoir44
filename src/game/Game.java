package game;

import java.util.List;
import java.util.Scanner;

public class Game {

    private Player axisPlayer;
    private Player alliedPlayer;
    private GameBoard gameBoard;

    public Game() {
        this.gameBoard  = new GameBoard();
    }

    public void startGame(){
        //Print initial game board
        while (true){
            this.gameRound();
        }
    }

    public void gameRound(){
        //Axis

        //Allied
    }

    public void assignEquipmentToPlayers(){
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
        System.out.println("Axis Player is: "+this.axisPlayer.getName());
        System.out.println("Allied Player is: "+this.alliedPlayer.getName());
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

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void initialize() {
        this.assignEquipmentToPlayers();
        this.gameBoard.initializeBoard();
        this.gameBoard.initializeEquipments(axisPlayer,alliedPlayer);
    }
}
