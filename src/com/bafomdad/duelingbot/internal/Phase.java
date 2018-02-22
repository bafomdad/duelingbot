package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.IPhase;
import com.bafomdad.duelingbot.enums.DuelPhase;

/**
 * Created by bafomdad on 2/20/2018.
 */
public abstract class Phase implements IPhase {

    public static class Draw extends Phase {

        private boolean canDraw = true;

        public boolean canDraw() {

            return canDraw;
        }

        public void setDraw(boolean flag) {

            this.canDraw = flag;
        }

        @Override
        public DuelPhase getPhase() {

            return DuelPhase.DRAW;
        }
    }

    public static class Standby extends Phase {

        @Override
        public DuelPhase getPhase() {

            return DuelPhase.STANDBY;
        }
    }

    public static class Main1 extends Phase {

        @Override
        public DuelPhase getPhase() {

            return DuelPhase.FIRST;
        }
    }

    public static class Battle extends Phase {

        @Override
        public DuelPhase getPhase() {

            return DuelPhase.BATTLE;
        }
    }

    public static class Main2 extends Phase {

        @Override
        public DuelPhase getPhase() {

            return DuelPhase.SECOND;
        }
    }

    public static class End extends Phase {

        @Override
        public DuelPhase getPhase() {

            return DuelPhase.END;
        }
    }
}
