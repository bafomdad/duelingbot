package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.api.IPosition;
import com.bafomdad.duelingbot.utils.PairUtil;
import com.bafomdad.duelingbot.utils.WrapperUtil;
import sx.blah.discord.handle.obj.IUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by bafomdad on 1/6/2018.
 */
public class Deck implements IPosition {

    private IUser owner;

    public static final int MAX_DECK_SIZE = 60;
    public static final int MIN_DECK_SIZE = 40;

    private boolean convulsed = false;

    private List<ICard> playerDeck = new ArrayList();
    private ExtraDeck extraDeck;

    public Deck(IUser owner) {

        this.owner = owner;
        this.loadDeck();
        this.loadExtraDeck();
    }

    public boolean canPlay() {

        return playerDeck.size() >= MIN_DECK_SIZE && playerDeck.size() <= MAX_DECK_SIZE;
    }

    public List<ICard> getDeck() {

        return playerDeck;
    }

    public boolean addCard(ICard card) {

        if (canAddCard(card)) {
            playerDeck.add(card);
            return true;
        }
        return false;
    }

    public boolean removeCard(ICard card) {

        for (ICard c : getDeck()) {
            if (c.getCardId().equals(card.getCardId())) {
                playerDeck.remove(c);
                return true;
            }
        }
        return false;
    }

    public void shuffle() {

        Collections.shuffle(playerDeck);
    }

    public ICard draw() {

        ICard toDraw = playerDeck.get(0);
        playerDeck.remove(0);
        return toDraw;
    }

    public boolean isConvulsed() {

        return convulsed;
    }

    public void setConvulsed(boolean flag) {

        this.convulsed = flag;
        Collections.reverse(playerDeck);
    }

    @Override
    public PairUtil getPosition() {

        return new PairUtil(212, 65);
    }

    private boolean canAddCard(ICard card) {

        int cardCount = 0;
        for (ICard c : getDeck()) {
            if (c.getCardId().equals(card.getCardId()))
                cardCount++;
        }
        return cardCount < card.getCardLimit();
    }

    public void loadDeck() {

        List<String> cardList = WrapperUtil.readDeckFromFile(owner.getName());
        if (cardList == null) return;

        for (String s : cardList) {
            WrapperUtil.add(this, s);
        }
    }

    public void loadExtraDeck() {

        extraDeck = new ExtraDeck(owner);
        for (ICard card : playerDeck) {
            switch (card.getProperty()) {
                case FUSION_MONSTER: extraDeck.addCard(card); playerDeck.remove(card);
                case EFFECT_FUSION: extraDeck.addCard(card); playerDeck.remove(card);
            }
        }
    }

    public ExtraDeck getExtraDeck() {

        return extraDeck;
    }
}
