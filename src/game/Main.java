package game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.getPlayersInfo();
        game.initialize();
        GameBoard.print();
        game.start();
    }
}