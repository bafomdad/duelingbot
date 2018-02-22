package com.bafomdad.duelingbot.cards;

import com.bafomdad.duelingbot.enums.CardTypes;
import com.bafomdad.duelingbot.enums.EnumProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 12/28/2017.
 */
public class TrapCard extends ACard {

    public TrapCard(String name, String desc, String property) {

        this.cardName = name;
        this.cardDescription = desc;
        this.cardProperty = property;
    }

    @Override
    public CardTypes getCardType() {

        return CardTypes.TRAP;
    }

    @Override
    public EnumProperty getProperty() {

        if (cardProperty == null) return EnumProperty.NORMAL_TRAP;

        for (EnumProperty t : EnumProperty.values()) {
            if (t.name().toLowerCase().equals(cardProperty.toLowerCase()))
                return t;
        }
        return EnumProperty.NORMAL_TRAP;
    }

    @Override
    public List<String> getCardInfo() {

        List<String> list = new ArrayList();
        list.add(getCardName());
        list.add("Property: " + getProperty().getName());
        list.add(getCardDescription());

        return list;
    }
}
