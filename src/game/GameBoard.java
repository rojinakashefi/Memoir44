
package game;

import java.util.ArrayList;
import java.util.List;

//naghsheye bazi
public class GameBoard {

    public static List<List<GameUnit>> gameBoard;
    private static final int numberOfCols = 13;
    private static final int numberOfRows = 9;

    public GameBoard() {
        initGameBoard();
    }

    private void initGameBoard() {
        gameBoard = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            gameBoard.add(new ArrayList<>());
        }
    }

    public static void printGameBoard() {
        //TODO-> not completed
        GameBoard.printGameUnit();
        GameBoard.printGameUnit();
        GameBoard.printGameUnit();
        GameBoard.printGameUnit();
        GameBoard.printGameUnit();
        GameBoard.printGameUnit();
    }

    public static void printGameUnit() {
        print("  * ** *  ");
        print(" *      * ");
        print("*        *");
        print(" *      * ");
        print("  * ** *  ");
    }

    private static void print(String text) {
        System.out.println(text);
    }


}