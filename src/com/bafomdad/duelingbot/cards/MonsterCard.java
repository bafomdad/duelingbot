package com.bafomdad.duelingbot.cards;

import com.bafomdad.duelingbot.enums.CardTypes;
import com.bafomdad.duelingbot.enums.EnumAttribute;
import com.bafomdad.duelingbot.enums.EnumProperty;
import com.bafomdad.duelingbot.enums.EnumRace;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 12/28/2017.
 */
public class MonsterCard extends ACard {

    @SerializedName("atk")
    private int attack;
    @SerializedName("def")
    private int defense;
    private int level;
    private String attribute;
    private String race;
    private boolean hasEffect;

    public MonsterCard() {}

    public MonsterCard(String name, String desc, String property, int attack, int defense, int level, String attribute, String race, boolean hasEffect) {

        this.cardName = name;
        this.cardDescription = desc;
        this.cardProperty = property;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.attribute = attribute;
        this.race = race;
        this.hasEffect = hasEffect;
    }

    @Override
    public CardTypes getCardType() {

        return CardTypes.MONSTER;
    }

    public EnumRace getRace() {

        if (race == null) return null;

        for (EnumRace r : EnumRace.values()) {
            if (r.getName().toLowerCase().equals(race.toLowerCase()))
                return r;
        }
        return null;
    }

    @Override
    public EnumProperty getProperty() {

        if (cardProperty == null) return EnumProperty.NORMAL_MONSTER;

        for (EnumProperty m : EnumProperty.values()) {
            if (m.name().toLowerCase().equals(cardProperty.toLowerCase()))
                return m;
        }
        return EnumProperty.NORMAL_MONSTER;
    }

    public EnumAttribute getAttribute() {

        if (attribute == null) return null;

        for (EnumAttribute a : EnumAttribute.values()) {
            if (a.name().toLowerCase().equals(attribute.toLowerCase()))
                return a;
        }
        return null;
    }

    public boolean hasEffect() {

        return hasEffect;
    }

    public String getStats() {

        return "ATK: " + this.attack + "/ DEF: " + this.defense;
    }

    @Override
    public List<String> getCardInfo() {

        List<String> list = new ArrayList();
        list.add(getCardName());
        list.add(getProperty().getName());
        list.add("Attribute: " + getAttribute());
        list.add("Level: " + level);
        list.add("Type: " + getRace().getName());
        list.add(getStats());
        list.add(getCardDescription());
        //list.add(hasEffect ? "EFFECTS: " + getCardDescription() : getCardDescription());

        return list;
    }

    public static MonsterCard Tokenize(String name, String race, String attribute, int level, int attack, int defense) {

        return new MonsterCard(name, "", "Token", attack, defense, level, attribute, race, false);
    }
}
