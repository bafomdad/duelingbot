package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.internal.Hand;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 2/17/2018.
 */
public class CommandPlay extends ACommand {

    private List<ICommand> subCommands = new ArrayList();

    public CommandPlay() {

        subCommands.add(new Surrender());
        subCommands.add(new Set());
    }

    @Override
    public String getName() {

        return "play";
    }

    @Override
    public Permissions getMinimumPermission() {

        return null;
    }

    @Override
    public boolean deleteCommandMessage() {

        return true;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        if (!DuelingBot.INSTANCE.getCurrentDuel().isDueling(sender)) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, sender.getName() + " is currently not in a duel.");
            return;
        }
        if (args.length < 2) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "Not enough arguments. Available arguments: " + subCommands.toString());
            return;
        }
        for (ICommand command : subCommands) {
            if (args[1].toLowerCase().equals(command.getName())) {
                command.execute(args, sender, channel);
                return;
            }
        }
        MessageUtil.send(DuelingBot.INSTANCE, channel, "Unknown argument. Available arguments: " + subCommands.toString());
    }

    private class Surrender extends ASubCommand {

        @Override
        public String getName() {

            return "surrender";
        }

        @Override
        public void execute(String[] args, IUser sender, IChannel channel) {

            MessageUtil.send(DuelingBot.INSTANCE, channel, sender.getName() + " has surrendered. " + DuelingBot.INSTANCE.getCurrentDuel().getOpposite(sender).getOwner().getName() + " wins the duel.");
            DuelingBot.INSTANCE.getCurrentDuel().stopDuel();
        }
    }

    private class Set extends ASubCommand {

        @Override
        public String getName() {

            return "set";
        }

        @Override
        public void execute(String[] args, IUser sender, IChannel channel) {

            if (args.length < 3) {
                MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !play summon [index of card in hand]");
                return;
            }
            int index = Integer.parseInt(args[2]);
            Hand hand = DuelingBot.INSTANCE.getCurrentDuel().getPlayingField(sender).getPlayerHand();
            if (index < 0 || index > hand.getHand().size()) {
                MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Index not in range.");
                return;
            }
            ICard card = hand.getHand().get(index);
            boolean flag = DuelingBot.INSTANCE.getCurrentDuel().getPlayingField(sender).canSetCard(card);
            if (flag) {
                MessageUtil.send(DuelingBot.INSTANCE, channel, sender.getName() + " has set a card on the field.");
                hand.discard(card);
                return;
            }
        }
    }
}
