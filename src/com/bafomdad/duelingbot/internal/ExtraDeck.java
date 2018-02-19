package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.IPosition;
import com.bafomdad.duelingbot.cards.ICard;
import com.bafomdad.duelingbot.utils.PairUtil;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 2/7/2018.
 */
public class ExtraDeck implements IPosition {

    private IUser owner;

    private List<ICard> extraDeck = new ArrayList();

    public ExtraDeck(IUser owner) {

        this.owner = owner;
    }

    public List<ICard> getExtraDeck() {

        return this.extraDeck;
    }

    public void addCard(ICard card) {

        extraDeck.add(card);
    }

    public void removeCard(ICard card) {

        extraDeck.remove(card);
    }

    public PairUtil getPosition() {

        return new PairUtil(4, 65);
    }
}
