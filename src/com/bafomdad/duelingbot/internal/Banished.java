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
public class Banished implements IPosition {

    private IUser owner;

    private List<ICard> banished = new ArrayList();

    public Banished(IUser owner) {

        this.owner = owner;
    }

    public List<ICard> getBanished() {

        return this.banished;
    }

    public void addCard(ICard card) {

        banished.add(card);
    }

    public void removeCard(ICard card) {

        banished.remove(card);
    }

    public PairUtil getPosition() {

        return new PairUtil(212, 1);
    }
}
