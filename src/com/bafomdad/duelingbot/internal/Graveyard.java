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
public class Graveyard implements IPosition {

    private IUser owner;

    private List<ICard> graveyard = new ArrayList();

    public Graveyard(IUser owner) {

        this.owner = owner;
    }

    public List<ICard> getGraveyard() {

        return this.graveyard;
    }

    public void addCard(ICard card) {

        graveyard.add(card);
    }

    public void removeCard(ICard card) {

        graveyard.remove(card);
    }

    public PairUtil getPosition() {

        return new PairUtil(212, 33);
    }
}
