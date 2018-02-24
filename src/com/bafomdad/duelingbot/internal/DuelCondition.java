package com.bafomdad.duelingbot.internal;

import com.bafomdad.duelingbot.api.ICondition;
import com.bafomdad.duelingbot.enums.DuelPhase;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by bafomdad on 2/24/2018.
 */
public class DuelCondition {

    private IUser player;
    private DuelPhase phase;
    private ICondition[] conditions;

    public DuelCondition(IUser player, DuelPhase phase, ICondition... conditions) {

        this.player = player;
        this.phase = phase;
        this.conditions = conditions;
    }

    public IUser getPlayer() {

        return player;
    }

    public DuelPhase getPhase() {

        return phase;
    }

    public ICondition[] getConditions() {

        return conditions;
    }
}
