package Dice;

import java.util.Date;
import java.util.Random;

public class dice {

    private static Random ranodm = new Random(new Date().hashCode());


    public static int roll() {
        return ranodm.nextInt(6) + 1;
    }
}
