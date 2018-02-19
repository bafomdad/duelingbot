package com.bafomdad.duelingbot.enums;

/**
 * Created by bafomdad on 12/28/2017.
 */
public enum EnumProperty {

    NORMAL_MONSTER("Normal Monster"),
    EFFECT_MONSTER("Effect Monster"),
    FLIP_EFFECT("Flip Effect Monster"),
    FUSION_MONSTER("Fusion Monster"),
    EFFECT_FUSION("Fusion Effect Monster"),
    RITUAL_MONSTER("Ritual Monster"),
    EFFECT_RITUAL("Ritual Effect Monster"),
    NORMAL_SPELL("Normal Spell"),
    CONTINUOUS_SPELL("Continuous Spell"),
    RITUAL_SPELL("Ritual Spell"),
    QUICK_SPELL("Quick-Play Spell"),
    FIELD_SPELL("Field Spell"),
    EQUIP_SPELL("Equip Spell"),
    NORMAL_TRAP("Normal Trap"),
    CONTINUOUS_TRAP("Continuous Trap"),
    COUNTER_TRAP("Counter Trap"),
    SPIRIT_MONSTER("Spirit Monster"),
    SYNCHRO_MONSTER("Synchro Monster"),
    SYNCHRO_TUNER("Synchro/Tuner Monster"),
    XYZ_MONSTER("XYZ Monster"),
    PENDULUM_MONSTER("Pendulum Monster"),
    TUNER_MONSTER("Tuner Monster"),
    GEMINI_MONSTER("Gemini Monster"),
    UNION_MONSTER("Union Monster"),
    TOON_MONSTER("Toon Monster");

    final String name;

    private EnumProperty(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
