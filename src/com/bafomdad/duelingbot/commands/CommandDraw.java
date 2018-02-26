package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.enums.DuelPhase;
import com.bafomdad.duelingbot.handlers.PhaseHandler;
import com.bafomdad.duelingbot.internal.Duel;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 2/9/2018.
 */
public class CommandDraw extends ACommand {

    @Override
    public String getName() {

        return "draw";
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
        Duel currentDuel = DuelingBot.INSTANCE.getCurrentDuel();
        if (currentDuel.getPlayingTurn().getCurrentPhase() != DuelPhase.DRAW) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "You are not in the draw phase.");
            return;
        }
        MessageUtil.send(DuelingBot.INSTANCE, channel, sender.getName() + " draws a card.");
        ICard card = currentDuel.getPlayingTurn().getPlayerDeck().draw();
        currentDuel.getPlayingTurn().getPlayerHand().add(card);
        PhaseHandler.cyclePhases(channel);
    }
}
