package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.cards.ICard;
import com.bafomdad.duelingbot.internal.Hand;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 2/17/2018.
 */
public class CommandPlay extends ACommand {

    @Override
    public String getName() {

        return "play";
    }

    @Override
    public Permissions getMinimumPermission() {

        return null;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        if (!DuelingBot.INSTANCE.getCurrentDuel().isDueling(sender)) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, sender.getName() + " is currently not in a duel.");
            return;
        }
        if (args.length < 2) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "Not enough arguments. Available arguments: surrender, set");
            return;
        }
        switch (args[1].toLowerCase()) {
            case "surrender": surrender(sender, channel); return;
            case "set": if (args.length < 3) summon("", sender, channel); else summon(args[2], sender, channel); break;
            default: MessageUtil.send(DuelingBot.INSTANCE, channel, "Unknown argument. Available arguments: surrender, set"); break;
        }
    }

    private void surrender(IUser player, IChannel channel) {

        MessageUtil.send(DuelingBot.INSTANCE, channel, player.getName() + " has surrendered. " + DuelingBot.INSTANCE.getCurrentDuel().getOpposite(player).getOwner().getName() + " wins the duel.");
        DuelingBot.INSTANCE.getCurrentDuel().stopDuel();
    }

    private void summon(String id, IUser sender, IChannel channel) {

        if (id.isEmpty()) {
            MessageUtil.sendPrivate(DuelingBot.INSTANCE, sender, "Proper arguments: !play summon [index of card in hand]");
            return;
        }
        int index = Integer.parseInt(id);
        Hand hand = DuelingBot.INSTANCE.getCurrentDuel().getPlayingField(sender).getPlayerHand();
        if (index < 0 || index > hand.getHand().size()) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, "Index not in range.");
            return;
        }
        ICard card = hand.getHand().get(index);
        boolean flag = DuelingBot.INSTANCE.getCurrentDuel().getPlayingField(sender).canSetCard(card);
        if (flag) {
            MessageUtil.send(DuelingBot.INSTANCE, channel, sender.getName() + " has set a card on the field.");
            hand.discard(card);
        }
    }
}
