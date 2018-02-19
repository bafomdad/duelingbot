package com.bafomdad.duelingbot.test;

import com.bafomdad.duelingbot.internal.Deck;
import com.bafomdad.duelingbot.utils.WrapperUtil;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by bafomdad on 2/6/2018.
 */
public class TestDeck extends Deck {

    public TestDeck(IUser owner) {

        super(owner);
        WrapperUtil.add(this, "barrel_dragon.json");
        WrapperUtil.add(this, "raigeki.json");
        WrapperUtil.add(this, "windstorm_of_etaqua.json");
    }
}
