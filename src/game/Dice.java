package game;

import java.util.Date;
import java.util.Random;

/**
 * Dice class
 * @author kashefi
 * @version 0.0
 */
public class Dice {
    //making random
    private static Random ranodm = new Random(new Date().hashCode());

    /**
     * making random number of dice
     * @return random number of dice
     */
    public static int roll() {
        return ranodm.nextInt(6) + 1;
    }

    /**
     * making random numbers
     * @param ceil as number we want have random between
     * @return random number we made
     */
    public static int roll(int ceil) {
        return ranodm.nextInt(ceil);
    }
}
