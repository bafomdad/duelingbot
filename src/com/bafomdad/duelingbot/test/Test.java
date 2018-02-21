package com.bafomdad.duelingbot.test;

import com.bafomdad.duelingbot.cards.*;
import com.bafomdad.duelingbot.utils.JsonUtil;

import com.bafomdad.duelingbot.utils.OutputUtil;
import com.bafomdad.duelingbot.utils.WrapperUtil;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by bafomdad on 12/28/2017.
 */
public class Test {

    private static File MONSTER_DIR = new File("monsters");
    private static File SPELL_DIR = new File("spells");
    private static File TRAP_DIR = new File("traps");

    public static void main(String[] args) throws IOException {

        //OutputUtil.convertCSVToJson(new File("csvs/ygopro-datas.csv"), true, false, false);
        //OutputUtil.addCardInfo(new File("csvs/ygopro-texts.csv"), true, false, false);
        //OutputUtil.addCardId();

//        if (TRAP_DIR.exists()) {
//            for (File f : TRAP_DIR.listFiles()) {
//                TrapCard trap = JsonUtil.fromJson(new TypeToken<TrapCard>(){}, f);
//                if (trap != null) {
//                    ScriptInternal.parse(trap.getScript());
//                }
//            }
//        }
    }
}
