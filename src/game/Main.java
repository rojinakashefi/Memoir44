package game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.getPlayersInfo();
        game.initialize();
        game.getGameBoard().print();
    }
}
