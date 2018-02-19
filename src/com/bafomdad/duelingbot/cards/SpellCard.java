package com.bafomdad.duelingbot.cards;

import com.bafomdad.duelingbot.enums.CardTypes;
import com.bafomdad.duelingbot.enums.EnumProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 12/28/2017.
 */
public class SpellCard extends ACard {

    public SpellCard() {}

    public SpellCard(String name, String desc, String property) {

        this.cardName = name;
        this.cardDescription = desc;
        this.cardProperty = property;
    }

    @Override
    public CardTypes getCardType() {

        return CardTypes.SPELL;
    }

    @Override
    public EnumProperty getProperty() {

        if (cardProperty == null) return EnumProperty.NORMAL_SPELL;

        for (EnumProperty m : EnumProperty.values()) {
            if (m.name().toLowerCase().equals(cardProperty.toLowerCase()))
                return m;
        }
        return EnumProperty.NORMAL_SPELL;
    }

    @Override
    public List<String> getCardInfo() {

        List<String> list = new ArrayList();
        list.add(getCardName());
        list.add(getProperty().getName());
        list.add(getCardDescription());

        return list;
    }
}
