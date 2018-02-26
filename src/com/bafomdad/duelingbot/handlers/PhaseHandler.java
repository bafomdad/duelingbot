package com.bafomdad.duelingbot.handlers;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.enums.DuelPhase;
import com.bafomdad.duelingbot.internal.Duel;
import com.bafomdad.duelingbot.internal.PlayingField;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by bafomdad on 2/26/2018.
 */
public class PhaseHandler {

    public static void cyclePhases(IChannel channel) {

        Duel duel = DuelingBot.INSTANCE.getCurrentDuel();
        duel.getPlayingTurn().cyclePhase();
        DuelPhase phase = duel.getPlayingTurn().getCurrentPhase();
        PlayingField player = duel.getPlayingTurn();
        switch (phase) {
            case DRAW: {
                MessageUtil.send(DuelingBot.INSTANCE, channel, player.getOwner().getName() + " enters their Draw Phase.");
            }
            case STANDBY: {
                MessageUtil.send(DuelingBot.INSTANCE, channel, player.getOwner().getName() + " entered their Standby Phase.");
                player.cyclePhase();
                cyclePhases(channel);
            }
            case FIRST: {
                MessageUtil.send(DuelingBot.INSTANCE, channel, player.getOwner().getName() + " entered Main Phase 1");
            }
            case BATTLE: {
                MessageUtil.send(DuelingBot.INSTANCE, channel, player.getOwner().getName() + " has started the Battle Phase.");
            }
            case SECOND: {
                MessageUtil.send(DuelingBot.INSTANCE, channel, player.getOwner().getName() + " entered Main Phase 2.");
            }
            case END: {
                PlayingField opponent = duel.getOpposite(player.getOwner());
                MessageUtil.send(DuelingBot.INSTANCE, channel, player.getOwner().getName() + " has ended their turn. " + opponent.getOwner().getName() + " takes their next turn.");
                duel.changeTurns();
                opponent.setPhase(DuelPhase.DRAW);
                cyclePhases(channel);
            }
        }
    }
}
