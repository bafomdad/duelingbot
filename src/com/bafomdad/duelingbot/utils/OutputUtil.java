package com.bafomdad.duelingbot.utils;

import com.bafomdad.duelingbot.cards.*;
import com.bafomdad.duelingbot.enums.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bafomdad on 2/14/2018.
 */
public class OutputUtil {

    private enum DATAS {
        ID,
        OT,
        ALIAS,
        SETCODE,
        TYPE,
        ATK,
        DEF,
        LEVEL,
        RACE,
        ATTRIBUTE,
        CATEGORY;
    }
    private enum TEXTS {
        ID,
        NAME,
        DESC;
    }

    private static Map<Integer, EnumAttribute> attributeMap = new HashMap();
    private static Map<Integer, EnumProperty> cardSubtypes = new HashMap();
    private static Map<Integer, EnumRace> raceMap = new HashMap();

    static {
        attributeMap.put(1, EnumAttribute.EARTH);
        attributeMap.put(2, EnumAttribute.WATER);
        attributeMap.put(4, EnumAttribute.FIRE);
        attributeMap.put(8, EnumAttribute.WIND);
        attributeMap.put(16, EnumAttribute.LIGHT);
        attributeMap.put(32, EnumAttribute.DARK);
        attributeMap.put(64, EnumAttribute.DIVINE);

        raceMap.put(1, EnumRace.WARRIOR);
        raceMap.put(2, EnumRace.SPELLCASTER);
        raceMap.put(4, EnumRace.FAIRY);
        raceMap.put(8, EnumRace.FIEND);
        raceMap.put(16, EnumRace.ZOMBIE);
        raceMap.put(32, EnumRace.MACHINE);
        raceMap.put(64, EnumRace.AQUA);
        raceMap.put(128, EnumRace.PYRO);
        raceMap.put(256, EnumRace.ROCK);
        raceMap.put(512, EnumRace.WINGED_BEAST);
        raceMap.put(1024, EnumRace.PLANT);
        raceMap.put(2048, EnumRace.INSECT);
        raceMap.put(4096, EnumRace.THUNDER);
        raceMap.put(8192, EnumRace.DRAGON);
        raceMap.put(16384, EnumRace.BEAST);
        raceMap.put(32768, EnumRace.BEAST_WARRIOR);
        raceMap.put(65536, EnumRace.DINOSAUR);
        raceMap.put(131072, EnumRace.FISH);
        raceMap.put(262144, EnumRace.SEA_SERPENT);
        raceMap.put(524288, EnumRace.REPTILE);
        raceMap.put(1048576, EnumRace.PSYCHIC);
        raceMap.put(2097152, EnumRace.DIVINE_BEAST);
        raceMap.put(4194304, EnumRace.CREATOR_GOD);
        raceMap.put(8388608, EnumRace.WYRM);

        cardSubtypes.put(2, EnumProperty.NORMAL_SPELL);
        cardSubtypes.put(4, EnumProperty.NORMAL_TRAP);
        cardSubtypes.put(17, EnumProperty.NORMAL_MONSTER);
        cardSubtypes.put(33, EnumProperty.EFFECT_MONSTER);
        cardSubtypes.put(65, EnumProperty.FUSION_MONSTER);
        cardSubtypes.put(97, EnumProperty.EFFECT_FUSION);
        cardSubtypes.put(129, EnumProperty.RITUAL_MONSTER);
        cardSubtypes.put(130, EnumProperty.RITUAL_SPELL);
        cardSubtypes.put(161, EnumProperty.EFFECT_RITUAL);
        cardSubtypes.put(545, EnumProperty.SPIRIT_MONSTER);
        cardSubtypes.put(1057, EnumProperty.UNION_MONSTER);
        cardSubtypes.put(2081, EnumProperty.GEMINI_MONSTER);
        cardSubtypes.put(4113, EnumProperty.TUNER_MONSTER);
        cardSubtypes.put(8225, EnumProperty.SYNCHRO_MONSTER);
        cardSubtypes.put(12321, EnumProperty.SYNCHRO_TUNER);
        cardSubtypes.put(65538, EnumProperty.QUICK_SPELL);
        cardSubtypes.put(131074, EnumProperty.CONTINUOUS_SPELL);
        cardSubtypes.put(131076, EnumProperty.CONTINUOUS_TRAP);
        cardSubtypes.put(262146, EnumProperty.EQUIP_SPELL);
        cardSubtypes.put(524290, EnumProperty.FIELD_SPELL);
        cardSubtypes.put(1048580, EnumProperty.COUNTER_TRAP);
        cardSubtypes.put(2097185, EnumProperty.FLIP_EFFECT);
        cardSubtypes.put(4194337, EnumProperty.TOON_MONSTER);
        cardSubtypes.put(8388641, EnumProperty.XYZ_MONSTER);
        cardSubtypes.put(16777249, EnumProperty.PENDULUM_MONSTER);
    }

    public static void convertCSVToJson(File f1, boolean readMonster, boolean readSpell, boolean readTrap) throws IOException {

        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);

        String temp;
        int i = 0;

        while ((temp = br.readLine()) != null) {
            i++;
            String[] dataSplitter = temp.split("\\t");

            String filename = dataSplitter[DATAS.ID.ordinal()];
            if (dataSplitter[DATAS.TYPE.ordinal()].equals("type")) continue;
            CardTypes type = getCardType(Integer.parseInt(dataSplitter[DATAS.TYPE.ordinal()]));
            if (type == CardTypes.UNDEFINED) continue;

            if (type == CardTypes.MONSTER && readMonster) {
                MonsterCard monster = new MonsterCard(null, null, cardSubtypes.get(parse(dataSplitter, DATAS.TYPE)).getName(), parse(dataSplitter, DATAS.ATK), parse(dataSplitter, DATAS.DEF), parse(dataSplitter, DATAS.LEVEL), attributeMap.get(parse(dataSplitter, DATAS.ATTRIBUTE)).name(), raceMap.get(parse(dataSplitter, DATAS.RACE)).getName(), hasEffect(cardSubtypes.get(parse(dataSplitter, DATAS.TYPE))));
                File f = new File("monsters/" + filename + ".json");
                JsonUtil.toJson(monster, new TypeToken<MonsterCard>(){}, f);
            }
            if (type == CardTypes.SPELL && readSpell) {
                SpellCard spell = new SpellCard(null, null, cardSubtypes.get(parse(dataSplitter, DATAS.TYPE)).getName());
                File f = new File("spells/" + filename + ".json");
                JsonUtil.toJson(spell, new TypeToken<SpellCard>() {}, f);
            }
            if (type == CardTypes.TRAP && readTrap) {
                TrapCard trap = new TrapCard(null, null, cardSubtypes.get(parse(dataSplitter, DATAS.TYPE)).getName());
                File f = new File("traps/" + filename + ".json");
                JsonUtil.toJson(trap, new TypeToken<TrapCard>() {}, f);
            }
        }
        System.out.println("Data Index: " + i);
        br.close();
        fr.close();
    }

    public static void addCardInfo(File f1, boolean readMonster, boolean readSpell, boolean readTrap) throws IOException {

        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);

        String temp;
        int j = 0;

        while ((temp = br.readLine()) != null) {
            j++;
            String[] textSplitter = temp.split("\\t");
            if (textSplitter[TEXTS.ID.ordinal()].equals("id")) continue;

            if (textSplitter.length > 2) {
                if (readMonster) writeNameAndDesc(WrapperUtil.MONSTER_DIR, textSplitter[TEXTS.ID.ordinal()], textSplitter[TEXTS.NAME.ordinal()], textSplitter[TEXTS.DESC.ordinal()]);
                if (readSpell) writeNameAndDesc(WrapperUtil.SPELL_DIR, textSplitter[TEXTS.ID.ordinal()], textSplitter[TEXTS.NAME.ordinal()], textSplitter[TEXTS.DESC.ordinal()]);
                if (readTrap) writeNameAndDesc(WrapperUtil.TRAP_DIR, textSplitter[TEXTS.ID.ordinal()], textSplitter[TEXTS.NAME.ordinal()], textSplitter[TEXTS.DESC.ordinal()]);
            }
        }
        System.out.println("Text Index: " + j);
        br.close();
        fr.close();
    }

    public static void addCardId() throws IOException {

        int index = 0;
        for (File f : WrapperUtil.MONSTER_DIR.listFiles()) {
            index++;
            String filename = f.getName().split(".json")[0];
            MonsterCard monster = JsonUtil.fromJson(new TypeToken<MonsterCard>(){}, f);
            monster.setCardId(filename);
            JsonUtil.toJson(monster, new TypeToken<MonsterCard>(){}, f);
        }
        for (File f: WrapperUtil.SPELL_DIR.listFiles()) {
            index++;
            String filename = f.getName().split(".json")[0];
            SpellCard spell = JsonUtil.fromJson(new TypeToken<SpellCard>(){}, f);
            spell.setCardId(filename);
            JsonUtil.toJson(spell, new TypeToken<SpellCard>(){}, f);
        }
        for (File f : WrapperUtil.TRAP_DIR.listFiles()) {
            index++;
            String filename = f.getName().split(".json")[0];
            TrapCard trap = JsonUtil.fromJson(new TypeToken<TrapCard>(){}, f);
            trap.setCardId(filename);
            JsonUtil.toJson(trap, new TypeToken<TrapCard>(){}, f);
        }
        System.out.println("Finishing count at: " + index);
    }

    private static void writeNameAndDesc(File dir, String filename, String cardName, String cardDesc) {

        File f = null;
        filename = filename + ".json";
        for (File file : dir.listFiles()) {
            if (file.getName().equals(filename)) {
                f = file;
                break;
            }
        }
        if (f == null) return;

        if (dir.getPath().equals(WrapperUtil.MONSTER_DIR.getPath())) {
            MonsterCard monster = JsonUtil.fromJson(new TypeToken<MonsterCard>(){}, f);
            monster.setCardName(cardName);
            monster.setCardDescription(cardDesc);
            JsonUtil.toJson(monster, new TypeToken<MonsterCard>(){}, f);
            return;
        }
        if (dir.getPath().equals(WrapperUtil.SPELL_DIR.getPath())) {
            SpellCard spell = JsonUtil.fromJson(new TypeToken<SpellCard>(){}, f);
            spell.setCardName(cardName);
            spell.setCardDescription(cardDesc);
            JsonUtil.toJson(spell, new TypeToken<SpellCard>(){}, f);
            return;
        }
        if (dir.getPath().equals(WrapperUtil.TRAP_DIR.getPath())) {
            TrapCard trap = JsonUtil.fromJson(new TypeToken<TrapCard>(){}, f);
            trap.setCardName(cardName);
            trap.setCardDescription(cardDesc);
            JsonUtil.toJson(trap, new TypeToken<TrapCard>(){}, f);
            return;
        }
    }

    private static CardTypes getCardType(int type) {

        EnumProperty prop = cardSubtypes.get(type);
        if (prop == null) return CardTypes.UNDEFINED;
        switch (prop) {
            case NORMAL_SPELL: return CardTypes.SPELL;
            case NORMAL_TRAP: return CardTypes.TRAP;
            case NORMAL_MONSTER: return CardTypes.MONSTER;
            case EFFECT_MONSTER: return CardTypes.MONSTER;
            case FUSION_MONSTER: return CardTypes.MONSTER;
            case EFFECT_FUSION: return CardTypes.MONSTER;
            case RITUAL_MONSTER: return CardTypes.MONSTER;
            case RITUAL_SPELL: return CardTypes.SPELL;
            case EFFECT_RITUAL: return CardTypes.MONSTER;
            case QUICK_SPELL: return CardTypes.SPELL;
            case CONTINUOUS_SPELL: return CardTypes.SPELL;
            case CONTINUOUS_TRAP: return CardTypes.TRAP;
            case EQUIP_SPELL: return CardTypes.SPELL;
            case FIELD_SPELL: return CardTypes.SPELL;
            case COUNTER_TRAP: return CardTypes.TRAP;
            case FLIP_EFFECT: return CardTypes.MONSTER;
            case TOON_MONSTER: return CardTypes.MONSTER;
            case UNION_MONSTER: return CardTypes.MONSTER;
            case SPIRIT_MONSTER: return CardTypes.MONSTER;
            case GEMINI_MONSTER: return CardTypes.MONSTER;
            default: return CardTypes.UNDEFINED;
        }
    }

    private static boolean hasEffect(EnumProperty prop) {

        switch (prop) {
            case EFFECT_MONSTER: return true;
            case EFFECT_FUSION: return true;
            case EFFECT_RITUAL: return true;
            case FLIP_EFFECT: return true;
        }
        return false;
    }

    private static int parse(String[] splitter, DATAS type) {

        if (splitter != null) {
            return Integer.parseInt(splitter[type.ordinal()]);
        }
        return -1;
    }
}
