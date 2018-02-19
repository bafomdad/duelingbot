package com.bafomdad.duelingbot.enums;

/**
 * Created by bafomdad on 1/5/2018.
 */
public enum EnumRace {

    WARRIOR("Warrior"),
    SPELLCASTER("Spellcaster"),
    FAIRY("Fairy"),
    FIEND("Fiend"),
    ZOMBIE("Zombie"),
    MACHINE("Machine"),
    AQUA("Aqua"),
    PYRO("Pyro"),
    ROCK("Rock"),
    WINGED_BEAST("Winged Beast"),
    PLANT("Plant"),
    INSECT("Insect"),
    THUNDER("Thunder"),
    DRAGON("Dragon"),
    BEAST("Beast"),
    BEAST_WARRIOR("Beast-Warrior"),
    DINOSAUR("Dinosaur"),
    FISH("Fish"),
    SEA_SERPENT("Sea Serpent"),
    REPTILE("Reptile"),
    PSYCHIC("Psychic"),
    DIVINE_BEAST("Divine-Beast"),
    CREATOR_GOD("Creator God"),
    WYRM("Wyrm");

    private String name;

    private EnumRace(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
