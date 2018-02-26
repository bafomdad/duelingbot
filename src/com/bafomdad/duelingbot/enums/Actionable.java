package com.bafomdad.duelingbot.enums;

import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.internal.PlayingField;

/**
 * Created by bafomdad on 12/27/2017.
 */
public enum Actionable {

    ACTIVATE {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    ATTACK {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    BANISH {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    BATTLE {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    BOUNCE {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    CHAIN {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    CHANGE_POSITION {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    DESTROY {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    DISCARD {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    DRAW {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    EQUIP {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    FLIP {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    LEAVE_FIELD {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    LOOK {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    NEGATE {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    POSSESS {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    RANDOM {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    RESPOND {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    RESOLVE {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    RETURN {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    REVEAL {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    SEND {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    SET {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    SHUFFLE {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    SPIN {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    SUMMON {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    TRIBUTE {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    },
    UNAFFECTED {
        @Override
        public boolean apply(PlayingField pf, ICard card) {

            return false;
        }
    };

    public abstract boolean apply(PlayingField pf, ICard card);

    public boolean checkConditions(ICard card) {

        return true;
    }
}
