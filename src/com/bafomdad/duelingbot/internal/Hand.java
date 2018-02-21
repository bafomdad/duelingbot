package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.ICard;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 1/6/2018.
 */
public class Hand {

    private IUser owner;

    public final int DRAW_HAND = 5;
    public final int MAX_HAND = 7;

    private boolean isVisible = false;

    private List<ICard> playerHand = new ArrayList();

    public Hand(IUser owner) {

        this.owner = owner;
    }

    public List<ICard> getHand() {

        return playerHand;
    }

    public void add(ICard card) {

        playerHand.add(card);
    }

    public void discard(ICard card) {

        playerHand.remove(card);
    }

    public String[] displayHand() {

        String[] list = new String[getHand().size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = getHand().get(i).getCardName();
        }
        return list;
    }

    public boolean isVisibleToOpponent() {

        return isVisible;
    }

    public void setVisibleToOpponent(boolean flag) {

        this.isVisible = flag;
    }
}
