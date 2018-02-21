package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.internal.Deck;
import com.bafomdad.duelingbot.utils.MessageUtil;
import com.bafomdad.duelingbot.utils.WrapperUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 2/11/2018.
 */
public class CommandEdit extends ACommand {

    private List<ICommand> subCommands = new ArrayList();

    public CommandEdit() {

        subCommands.add(new Add());
        subCommands.add(new Remove());
        subCommands.add(new View());
        subCommands.add(new Show());
        subCommands.add(new Clear());
    }

    @Override
    public String getName() {

        return "deckedit";
    }

    @Override
    public Permissions getMinimumPermission() {

        return null;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        if (DuelingBot.INSTANCE.getCurrentDuel().isDueling(sender)) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "You cannot edit your deck while you are in a duel.");
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

    private class Add extends ASubCommand {

        @Override
        public String getName() {

            return "add";
        }

        @Override
        public void execute(String[] args, IUser sender, IChannel channel) {

            if (args.length < 3) {
                MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !deckedit add [card id]");
                return;
            }
            ICard card = WrapperUtil.getCard(args[2]);
            if (card != null) {
                Deck deck = new Deck(sender);
                if (deck.getDeck().size() == 60) {
                    MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Cannot add card [" + card.getCardName() + "]: Deck size limit reached.");
                    return;
                }
                boolean flag = deck.addCard(card);
                WrapperUtil.writeDeckToFile(deck, sender.getName());
                if (!flag) {
                    MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Cannot add this card [" + card.getCardName() + "]: Multiple or enough copies of it already exists.");
                    return;
                }
                else {
                    MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Card [" + card.getCardName() + "] added successfully.");
                    return;
                }
            }
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Card ID was invalid.");
        }
    }

    private class Remove extends ASubCommand {

        @Override
        public String getName() {

            return "remove";
        }

        @Override
        public void execute(String[] args, IUser sender, IChannel channel) {

            if (args.length < 3) {
                MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !deckedit remove [card id]");
                return;
            }
            if (args[2].length() < 3) {
                Deck deck = new Deck(sender);
                int index = Integer.parseInt(args[2]);
                if (index < 0 || index > deck.getDeck().size()) {
                    MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Index is out of range.");
                    return;
                }
                ICard card = deck.getDeck().get(index);
                MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "[" + card.getCardName() + "] removed from your deck.");
                deck.getDeck().remove(index);
                WrapperUtil.writeDeckToFile(deck, sender.getName());
                return;
            }
            ICard card = WrapperUtil.getCard(args[2]);
            if (card != null) {
                Deck deck = new Deck(sender);
                boolean flag = deck.removeCard(card);
                WrapperUtil.writeDeckToFile(deck, sender.getName());
                if (!flag) {
                    MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Card [" + card.getCardName() + "] does not exist in your deck.");
                    return;
                }
                else {
                    MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "[" + card.getCardName() + "] removed from your deck.");
                    return;
                }
            }
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Card ID was invalid.");
        }
    }

    private class Show extends ASubCommand {

        @Override
        public String getName() {

            return "show";
        }

        @Override
        public void execute(String[] args, IUser sender, IChannel channel) {

            Deck deck = new Deck(sender);
            MessageUtil.updateDeck(DuelingBot.INSTANCE, sender, deck);
        }
    }

    private class Clear extends ASubCommand {

        @Override
        public String getName() {

            return "clear";
        }

        @Override
        public void execute(String[] args, IUser sender, IChannel channel) {

            Deck deck = new Deck(sender);
            deck.getDeck().clear();
            WrapperUtil.writeDeckToFile(deck, sender.getName());
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Deck is now cleared of contents.");
        }
    }

    private class View extends ASubCommand {

        @Override
        public String getName() {

            return "view";
        }

        @Override
        public void execute(String[] args, IUser sender, IChannel channel) {

            if (args.length < 3) {
                MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !deckedit view [index of card in deck list]");
                return;
            }
            int index = Integer.parseInt(args[2]);
            Deck deck = new Deck(sender);
            if (index < 0 || index > deck.getDeck().size()) {
                MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Index is out of range.");
                return;
            }
            ICard card = deck.getDeck().get(index);
            MessageUtil.showCard(DuelingBot.INSTANCE, sender, card);
        }
    }
}
