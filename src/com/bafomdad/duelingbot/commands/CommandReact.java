package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 2/24/2018.
 */
public class CommandReact extends ACommand {

    @Override
    public String getName() {

        return "react";
    }

    @Override
    public Permissions getMinimumPermission() {

        return null;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        MessageUtil.sendWithReaction(DuelingBot.INSTANCE, channel, sender.getName() + " has reacted.", ":cardBack:417220577502429184");
    }
}
