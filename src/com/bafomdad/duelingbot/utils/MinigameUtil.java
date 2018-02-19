package com.bafomdad.duelingbot.utils;

import java.util.Random;

/**
 * Created by bafomdad on 1/6/2018.
 */
public class MinigameUtil {

    public static boolean coinFlip() {

        Random rand = new Random();
        return rand.nextBoolean();
    }

    public static int rollDice() {

        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }
}
