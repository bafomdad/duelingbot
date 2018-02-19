package com.bafomdad.duelingbot.utils;

import com.bafomdad.duelingbot.cards.*;
import com.bafomdad.duelingbot.internal.Deck;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bafomdad on 2/6/2018.
 */
public class WrapperUtil {

    public static File MONSTER_DIR = new File("monsters");
    public static File SPELL_DIR = new File("spells");
    public static File TRAP_DIR = new File("traps");
    private static File DECK_DIR = new File("decks");

    public static void add(Deck deck, String name) {

        if (name.startsWith("0"))
            name = name.substring(1);
        name = name + ".json";

        if (MONSTER_DIR.exists() && SPELL_DIR.exists() && TRAP_DIR.exists()) {
            File monsterFile = new File(MONSTER_DIR + "/" + name);
            File spellFile = new File(SPELL_DIR + "/" + name);
            File trapFile = new File(TRAP_DIR + "/" + name);

            MonsterCard monster = JsonUtil.fromJson(new TypeToken<MonsterCard>(){}, monsterFile);
            if (monster != null) {
                deck.addCard(monster);
                return;
            }
            SpellCard spell = JsonUtil.fromJson(new TypeToken<SpellCard>(){}, spellFile);
            if (spell != null) {
                deck.addCard(spell);
                return;
            }
            TrapCard trap = JsonUtil.fromJson(new TypeToken<TrapCard>(){}, trapFile);
            if (trap != null) {
                deck.addCard(trap);
                return;
            }
//            for (File f : MONSTER_DIR.listFiles()) {
//                if (f.getName().equals(name)) {
//                    MonsterCard monsterCard = JsonUtil.fromJson(new TypeToken<MonsterCard>(){}, f);
//                    if (monsterCard != null) {
//                        deck.addCard(monsterCard);
//                        return;
//                    }
//                }
//            }
//            for (File f : SPELL_DIR.listFiles()) {
//                if (f.getName().equals(name)) {
//                    SpellCard spellCard = JsonUtil.fromJson(new TypeToken<SpellCard>(){}, f);
//                    if (spellCard != null) {
//                        deck.addCard(spellCard);
//                        return;
//                    }
//                }
//            }
//            for (File f : TRAP_DIR.listFiles()) {
//                if (f.getName().equals(name)) {
//                    TrapCard trapCard = JsonUtil.fromJson(new TypeToken<TrapCard>(){}, f);
//                    if (trapCard != null) {
//                        deck.addCard(trapCard);
//                        return;
//                    }
//                }
//            }
        }
    }

    public static void writeDeckToFile(List<String> list, String username) {

        FileWriter fw;
        if (!DECK_DIR.exists()) DECK_DIR.mkdir();

        try {
            fw = new FileWriter(new File(DECK_DIR + "/" + username + ".txt"));
            if (!list.isEmpty()) {
                for (String f : list) {
                    fw.write(f);
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDeckToFile(Deck deck, String username) {

        FileWriter fw;
        if (!DECK_DIR.exists()) DECK_DIR.mkdir();

        try {
            fw = new FileWriter(new File(DECK_DIR + "/" + username + ".txt"));
            if (!deck.getDeck().isEmpty()) {
                for (ICard card : deck.getDeck()) {
                    fw.write(card.getCardId());
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readDeckFromFile(String username) {

        List<String> temp = new ArrayList();
        if (!DECK_DIR.exists()) {
            writeDeckToFile(temp, username);
            return temp;
        }
        File deck = null;

        for (File f : DECK_DIR.listFiles()) {
            if (f.getName().equals(username + ".txt")) {
                deck = f;
                break;
            }
        }
        if (deck == null) {
            writeDeckToFile(temp, username);
            return temp;
        }
        else if (deck != null) {
            BufferedReader br = null;
            FileReader fr = null;
            List<String> cardList = new ArrayList();

            try {
                fr = new FileReader(deck);
                br = new BufferedReader(fr);

                String readLine;
                while ((readLine = br.readLine()) != null)
                    cardList.add(readLine);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();

                    return cardList;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static ICard getCard(String name) {

        if (name.startsWith("0"))
            name = name.substring(1);

        name = name + ".json";

        if (MONSTER_DIR.exists() && SPELL_DIR.exists() && TRAP_DIR.exists()) {
            File monsterFile = new File(MONSTER_DIR + "/" + name);
            File spellFile = new File(SPELL_DIR + "/" + name);
            File trapFile = new File(TRAP_DIR + "/" + name);

            MonsterCard monster = JsonUtil.fromJson(new TypeToken<MonsterCard>(){}, monsterFile);
            if (monster != null) return monster;
            SpellCard spell = JsonUtil.fromJson(new TypeToken<SpellCard>(){}, spellFile);
            if (spell != null) return spell;
            TrapCard trap = JsonUtil.fromJson(new TypeToken<TrapCard>(){}, trapFile);
            if (trap != null) return trap;
        }
//        if (monsterList != null) {
//            for (File f : monsterList) {
//                if (f.isDirectory()) continue;
//                else if (name.equalsIgnoreCase(f.getName())) {
//                    MonsterCard monsterCard = JsonUtil.fromJson(new TypeToken<MonsterCard>(){}, f);
//                    if (monsterCard != null) return monsterCard;
//                }
//            }
//        }
//        if (spellList != null) {
//            for (File f : spellList) {
//                if (f.isDirectory()) continue;
//                else if (name.equalsIgnoreCase(f.getName())) {
//                    SpellCard spellCard = JsonUtil.fromJson(new TypeToken<SpellCard>(){}, f);
//                    if (spellCard != null) return spellCard;
//                }
//            }
//        }
//        if (trapList != null) {
//            for (File f : trapList) {
//                if (f.isDirectory()) continue;
//                else if (name.equalsIgnoreCase(f.getName())) {
//                    TrapCard trapCard = JsonUtil.fromJson(new TypeToken<TrapCard>(){}, f);
//                    if (trapCard != null) return trapCard;
//                }
//            }
//        }
        return null;
    }
}
