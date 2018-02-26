package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.handle.obj.StatusType;

import java.util.List;

/**
 * Created by bafomdad on 1/15/2018.
 */
public class CommandDuelOld extends ACommand {

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

        if (args.length < 2 && (DuelingBot.INSTANCE.getCurrentDuel().isDuelStarted() || DuelingBot.INSTANCE.getCurrentDuel().canPlay()) && sender.getPermissionsForGuild(channel.getGuild()).contains(Permissions.ADMINISTRATOR)) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "Duel is already started / queued. Stopping duel...");
            DuelingBot.INSTANCE.getCurrentDuel().stopDuel();
            return;
        }
        if (args.length < 2) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "Not enough arguments. Please declare another valid username.");
            return;
        }
        List<IUser> mentions = channel.getMessageHistory().getLatestMessage().getMentions();
        if (mentions != null && !mentions.isEmpty()) {
            IUser user = mentions.get(0);
            if (user.getPresence().getStatus() == StatusType.OFFLINE || user.getPresence().getStatus() == StatusType.DND) {
                MessageUtil.send(DuelingBot.INSTANCE, channel, "Cannot start a duel with a user that is offline or in Do Not Disturb mode.");
                return;
            }
            boolean addPlayer = DuelingBot.INSTANCE.getCurrentDuel().addPlayer(sender);
            if (addPlayer) {
                MessageUtil.send(DuelingBot.INSTANCE, channel, "Duelist " + sender.getName() + " added to match. Waiting for other player to join..");
                DuelingBot.INSTANCE.getCurrentDuel().addPlayer(sender);
            }
            else {
                MessageUtil.send(DuelingBot.INSTANCE, channel, "Cannot add player to duel: Duel queue is filled.");
                return;
            }
            boolean canDuel = DuelingBot.INSTANCE.getCurrentDuel().canPlay();
            if (canDuel) {
                if (!DuelingBot.INSTANCE.getCurrentDuel().canStartDuel()) {
                    MessageUtil.send(DuelingBot.INSTANCE, channel, "Duel cannot start. One or two participants do not have a valid deck.");
                    return;
                }
                MessageUtil.send(DuelingBot.INSTANCE, channel, "Duel has started. Participants: " + sender.mention() + " and " + user.mention());
                DuelingBot.INSTANCE.getCurrentDuel().startDuel();
                MessageUtil.send(DuelingBot.INSTANCE, channel, "Player " + DuelingBot.INSTANCE.getCurrentDuel().getPlayingTurn().getOwner().getName() + " takes first turn.");
            }
        }
    }
}
