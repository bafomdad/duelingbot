package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.IPosition;
import com.bafomdad.duelingbot.cards.ICard;
import com.bafomdad.duelingbot.enums.DuelZone;
import com.bafomdad.duelingbot.utils.PairUtil;

/**
 * Created by bafomdad on 2/6/2018.
 */
public class FieldObject implements IPosition {

    private ICard card = null;
    private ICard equippedCard = null;
    private DuelZone zone = DuelZone.UNDEFINED;

    private boolean faceDown;
    private boolean defense;
    private boolean specialSummon;
    private int counter = 0;
    private int attackCounter = 1;

    public FieldObject(ICard card, DuelZone zone) {

        this.card = card;
        this.zone = zone;
    }

    public ICard getCard() {

        return card;
    }

    public DuelZone getZoneType() {

        return zone;
    }

    public void setZoneType(DuelZone zone) {

        this.zone = zone;
    }

    public boolean isFaceDown() {

        return faceDown;
    }

    public boolean isDefensePosition() {

        return defense;
    }

    public void setFaceDown() {

        this.faceDown = true;
    }

    public void setFaceDownDefense() {

        this.faceDown = true;
        this.defense = true;
    }

    public void shiftPosition() {

        this.defense = !defense;
    }

    public boolean isFacedownDefense() {

        return faceDown && defense;
    }

    public boolean isEquipped() {

        return equippedCard != null;
    }

    public ICard getEquippedCard() {

        return equippedCard;
    }

    public void setEquippedCard(ICard equip) {

        this.equippedCard = equip;
    }

    public void setSpecialSummoned(boolean flag) {

        this.specialSummon = flag;
    }

    public boolean isSpecialSummoned() {

        return this.specialSummon;
    }

    public int getCounter() {

        return counter;
    }

    public void addCounter() {

        this.counter++;
    }

    public void removeCounter() {

        this.counter--;
    }

    public int getAttackCounter() {

        return attackCounter;
    }

    public void setAttackCounter(int count) {

        this.attackCounter += count;
    }

    public PairUtil getPosition() {

        switch (getZoneType()) {
            case FIELD: return new PairUtil(4, 33);
            case MONSTER: return new PairUtil(64, 25);
            case SPELLANDTRAPS: return new PairUtil(64, 56);
            default: return new PairUtil(0, 0);
        }
    }
}
