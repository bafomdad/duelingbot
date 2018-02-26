package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.internal.Hand;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.util.Arrays;

/**
 * Created by bafomdad on 1/16/2018.
 */
public class CommandHand extends ACommand {

    @Override
    public String getName() {

        return "hand";
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
        Hand hand = DuelingBot.INSTANCE.getCurrentDuel().getPlayingField(sender).getPlayerHand();
        if (hand.getHand().isEmpty()) {
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "You currently have no cards in your hand.");
            return;
        }
        MessageUtil.showHand(DuelingBot.INSTANCE, channel, sender, hand);
    }
}
