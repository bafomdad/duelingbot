package com.bafomdad.duelingbot.api;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 1/15/2018.
 */
public interface ICommand {

    public String getName();

    public Permissions getMinimumPermission();

    public void execute(String[] args, IUser sender, IChannel channel);
}
