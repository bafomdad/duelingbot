package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.internal.PlayingField;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 1/17/2018.
 */
public class CommandField extends ACommand {

    @Override
    public String getName() {

        return "field";
    }

    @Override
    public Permissions getMinimumPermission() {

        return null;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        if (!DuelingBot.INSTANCE.getCurrentDuel().isDueling(sender)) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "You are currently not in a duel.");
            return;
        }
        for (PlayingField pf : DuelingBot.INSTANCE.getCurrentDuel().getPlayers()) {
            MessageUtil.updateField(DuelingBot.INSTANCE, channel, pf);
        }
//        PlayingField pf = DuelingBot.INSTANCE.getCurrentDuel().getPlayingField(sender);
//        MessageUtil.updateField(DuelingBot.INSTANCE, channel, pf);
    }
}
