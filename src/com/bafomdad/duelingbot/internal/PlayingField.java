package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.enums.*;
import sx.blah.discord.handle.obj.IUser;

import java.util.Arrays;

/**
 * Created by bafomdad on 1/17/2018.
 */
public class PlayingField {

    private IUser owner;
    private int lifePoints = 8000;
    private int currentPhase = 0;

    private Deck playerDeck;
    private Hand playerHand;
    private Graveyard graveyard;
    private Banished banished;

    private FieldObject[] fieldZone = new FieldObject[1];
    private FieldObject[] monsterZone = new FieldObject[5];
    private FieldObject[] spellZone = new FieldObject[5];

    public PlayingField(IUser owner) {

        this.owner = owner;
        this.setOwner();
        Arrays.fill(fieldZone, new FieldObject(null, DuelZone.FIELD));
        Arrays.fill(monsterZone, new FieldObject(null, DuelZone.MONSTER));
        Arrays.fill(spellZone, new FieldObject(null, DuelZone.SPELLANDTRAPS));
    }

    private void setOwner() {

        playerDeck = new Deck(this.owner);
        playerHand = new Hand(this.owner);
        graveyard = new Graveyard(this.owner);
        banished = new Banished(this.owner);
 //       extraDeck = new ExtraDeck(this.owner);
    }

    public IUser getOwner() {

        return owner;
    }

    public int getLifePoints() {

        return lifePoints;
    }

    public void setLifePoints(int count) {

        this.lifePoints += count;
    }

    public Deck getPlayerDeck() {

        return playerDeck;
    }

    public ExtraDeck getExtraDeck() {

        return playerDeck.getExtraDeck();
    }

    public Hand getPlayerHand() {

        return playerHand;
    }

    public Graveyard getGraveyard() {

        return graveyard;
    }

    public Banished getBanished() {

        return banished;
    }

    public FieldObject[] getFieldZone() {

        return fieldZone;
    }

    public FieldObject[] getMonsterZone() {

        return monsterZone;
    }

    public FieldObject[] getSpellZone() {

        return spellZone;
    }

    public boolean canSetCard(ICard card) {

        switch (card.getCardType()) {
            case MONSTER: return canSetMonsterCard(card);
            case SPELL: if (card.getProperty() == EnumProperty.FIELD_SPELL) return canSetFieldCard(card); return canSetSpellCard(card);
            case TRAP: return canSetSpellCard(card);
            default: return false;
        }
    }

    public boolean canSetFieldCard(ICard card) {

        if (card == null || card.getCardType() != CardTypes.SPELL || card.getProperty() != EnumProperty.FIELD_SPELL) return false;

        return fieldZone[0] == null;
    }

    public boolean canSetMonsterCard(ICard card) {

        if (card == null || card.getCardType() != CardTypes.MONSTER) return false;

        for (int i = 0; i < monsterZone.length; i++) {
            ICard loopCard = monsterZone[i].getCard();
            if (loopCard == null) {
                monsterZone[i] = new FieldObject(card, DuelZone.MONSTER);
                monsterZone[i].setCardPosition(CardPosition.FACE_DOWN_DEFENSE);
                return true;
            }
        }
        return false;
    }

    public boolean canSetSpellCard(ICard card) {

        if (card == null || card.getCardType() == CardTypes.MONSTER) return false;

        for (int i = 0; i < spellZone.length; i++) {
            ICard loopCard = spellZone[i].getCard();
            if (loopCard == null) {
                spellZone[i] = new FieldObject(card, DuelZone.SPELLANDTRAPS);
                spellZone[i].setCardPosition(CardPosition.FACE_DOWN);
                return true;
            }
        }
        return false;
    }

    public void cyclePhase() {

        this.currentPhase++;
    }

    public void setPhase(DuelPhase phase) {

        this.currentPhase = phase.ordinal();
    }

    public DuelPhase getCurrentPhase() {

        return DuelPhase.values()[currentPhase];
    }
}
