package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.cards.ICard;
import com.bafomdad.duelingbot.internal.Deck;
import com.bafomdad.duelingbot.utils.MessageUtil;
import com.bafomdad.duelingbot.utils.WrapperUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 2/11/2018.
 */
public class CommandEdit extends ACommand {

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
            MessageUtil.send(DuelingBot.INSTANCE, channel, "Not enough arguments. Available arguments: add, remove, show, clear, view");
            return;
        }
        switch (args[1].toLowerCase()) {
            case "add": if (args.length < 3) add("", sender); else add(args[2], sender); break;
            case "remove": if (args.length < 3) remove("", sender); else remove(args[2], sender); break;
            case "view": if (args.length < 3) view("", sender); else view(args[2], sender); break;
            case "show": show(sender); break;
            case "clear": clear(sender); break;
            default: MessageUtil.send(DuelingBot.INSTANCE, channel, "Unknown argument. Available arguments: add, remove, show, clear, view"); break;
        }
    }

    public void add(String id, IUser sender) {

        if (id.isEmpty()) {
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !deckedit add [card id]");
            return;
        }
        ICard card = WrapperUtil.getCard(id);
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

    public void remove(String id, IUser sender) {

        if (id.isEmpty()) {
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !deckedit remove [card id]");
            return;
        }
        if (id.length() < 3) {
            Deck deck = new Deck(sender);
            int index = Integer.parseInt(id);
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
        ICard card = WrapperUtil.getCard(id);
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

    public void show(IUser sender) {

        Deck deck = new Deck(sender);
        MessageUtil.updateDeck(DuelingBot.INSTANCE, sender, deck);
    }

    public void clear(IUser sender) {

        Deck deck = new Deck(sender);
        deck.getDeck().clear();
        MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Deck is now cleared of contents.");
    }

    public void view(String id, IUser sender) {

        if (id.isEmpty()) {
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !deckedit view [index of card in deck list]");
            return;
        }
        int index = Integer.parseInt(id);
        Deck deck = new Deck(sender);
        if (index < 0 || index > deck.getDeck().size()) {
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Index is out of range.");
            return;
        }
        ICard card = deck.getDeck().get(index);
        MessageUtil.showCard(DuelingBot.INSTANCE, sender, card);
    }
}
