package com.bafomdad.duelingbot.enums;

import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.internal.FieldObject;
import com.bafomdad.duelingbot.internal.PlayingField;

/**
 * Created by bafomdad on 12/27/2017.
 */
public enum Actionable {

    ACTIVATE {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    ATTACK {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    BANISH {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    BATTLE {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    BOUNCE {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    CHAIN {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    CHANGE_POSITION {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    DESTROY {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    DISCARD {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    DRAW {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    EQUIP {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    FLIP {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            if (type instanceof Integer) {
                Integer index = (Integer)type;
                if (pf.getMonsterZone()[index.intValue()].getCardPosition() == CardPosition.FACE_DOWN_DEFENSE) {
                    pf.getMonsterZone()[index.intValue()].setCardPosition(CardPosition.FACE_UP);
                    return true;
                }
            }
            return false;
        }
    },
    LEAVE_FIELD {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    LOOK {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    NEGATE {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    POSSESS {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    RANDOM {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    RESPOND {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    RESOLVE {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    RETURN {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    REVEAL {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    SEND {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    SET {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            if (type instanceof ICard) {
                return pf.canSetCard((ICard)type);
            }
            return false;
        }
    },
    SHUFFLE {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            pf.getPlayerDeck().shuffle();
            return true;
        }
    },
    SPIN {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    },
    SUMMON {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            if (type instanceof ICard) {
                ICard card = (ICard)type;
                if (card.getCardType() != CardTypes.MONSTER) return false;

//                MonsterCard monster = (MonsterCard)card;
                for (int i = 0; i < pf.getMonsterZone().length; i++) {
                    ICard loopcard = pf.getMonsterZone()[i].getCard();
                    if (loopcard == null) {
                        pf.getMonsterZone()[i] = new FieldObject(card, DuelZone.MONSTER);
                        return true;
                    }
                }
            }
            return false;
        }
    },
    TRIBUTE {
        @Override
        public <T> boolean apply(PlayingField pf, T card) {

            return false;
        }
    },
    UNAFFECTED {
        @Override
        public <T> boolean apply(PlayingField pf, T type) {

            return false;
        }
    };

    public abstract <T> boolean apply(PlayingField pf, T card);

    public boolean checkConditions(ICard card) {

        return true;
    }
}
