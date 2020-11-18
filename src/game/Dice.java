package game;

import java.util.Date;
import java.util.Random;

public class Dice {

    private static Random ranodm = new Random(new Date().hashCode());


    public static int roll() {
        return ranodm.nextInt(6) + 1;
    }

    public static int roll(int ceil) {
        return ranodm.nextInt(ceil);
    }
}
