package com.bafomdad.duelingbot.api;

import com.bafomdad.duelingbot.enums.CardTypes;
import com.bafomdad.duelingbot.enums.EnumProperty;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

/**
 * Created by bafomdad on 1/5/2018.
 */
public interface ICard {

    public CardTypes getCardType();

    public List<String> getCardInfo();

    public EnumProperty getProperty();

    public String getCardName();

    public String getCardId();

    public int getCardLimit();

    public IUser getOriginalOwner();

    public void setOriginalOwner(IUser user);
}
