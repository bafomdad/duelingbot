package com.bafomdad.duelingbot.internal;

import java.util.List;

/**
 * Created by bafomdad on 1/12/2018.
 */
public class ScriptInternal {

    public static String PATTERN = "-?\\d+";

    public static void parse(List<String> scriptList) {

        if (scriptList == null || scriptList.isEmpty()) return;

        for (String s : scriptList) {
            if (!s.isEmpty() && s.matches(PATTERN)) {
                switch (Integer.parseInt(s)) {
                    case 131: System.out.println("Hello from this script!"); break;
                    case 12: System.out.println("Hello"); break;
                    case 1: System.out.println("Yes"); break;
                    case 8: System.out.println("This"); break;
                    case 42: System.out.println("Is"); break;
                    case 100: System.out.println("Dog"); break;
                    default: System.out.println("There is nothing I can do for you, Dave."); break;
                }
            }
        }
    }

    public static boolean isInteger(String s) {

        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {

        if (s.isEmpty()) return false;

        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }
}
