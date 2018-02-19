package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 1/15/2018.
 */
public class CommandShutdown extends ACommand {

    @Override
    public String getName() {

        return "shutdown";
    }

    @Override
    public Permissions getMinimumPermission() {

        return Permissions.ADMINISTRATOR;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        MessageUtil.send(DuelingBot.INSTANCE, channel, "Shutting down the bot...");
        System.exit(0);
    }
}
