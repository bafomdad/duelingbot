package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.internal.Duel;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 2/25/2018.
 */
public class CommandDuel extends ACommand {

    @Override
    public String getName() {

        return "duel";
    }

    @Override
    public Permissions getMinimumPermission() {

        return null;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        if (args.length < 2 && channel.getMessageByID(DuelingBot.INSTANCE.getDuelQueue()) == null) {
            MessageUtil.sendWithReaction(DuelingBot.INSTANCE, channel, "A duel has been queued up. Duelists: " + "[Empty] / [Empty]", ":cardBack:417220577502429184");
            return;
        }
        if (args.length < 2 && channel.getMessageByID(DuelingBot.INSTANCE.getDuelQueue()) != null) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "A duel queue is already in effect.");
            return;
        }
        if (args.length > 1 && args[1].equals("stop") /*&& DuelingBot.INSTANCE.getCurrentDuel().isDuelStarted()*/ && sender.getPermissionsForGuild(channel.getGuild()).contains(Permissions.ADMINISTRATOR)) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "Duel queue is already started / queued. Stopping duel...");
            DuelingBot.INSTANCE.getCurrentDuel().stopDuel();
            //DuelingBot.INSTANCE.setDuelQueue(-1);
            return;
        }
    }
}
