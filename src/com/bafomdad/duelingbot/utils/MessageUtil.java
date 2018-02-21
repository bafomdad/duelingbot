package com.bafomdad.duelingbot.utils;

import com.bafomdad.duelingbot.api.IDuelingBot;
import com.bafomdad.duelingbot.api.ICard;
import com.bafomdad.duelingbot.internal.Deck;
import com.bafomdad.duelingbot.internal.PlayingField;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.util.List;

/**
 * Created by bafomdad on 1/15/2018.
 */
public class MessageUtil {

    public static void send(IDuelingBot chat, IChannel channel, String message) {

        RequestBuffer.request(() ->
        new MessageBuilder(chat.getClient()).withChannel(channel).withContent(message).build());
    }

    public static void sendWithReaction(IDuelingBot chat, IChannel channel, String message, String reaction) {

        RequestBuffer.request(() ->
        new MessageBuilder(chat.getClient()).withChannel(channel).withContent(message).build().addReaction(reaction));
    }

    public static void sendPrivate(IDuelingBot chat, IUser user, String message) {

        RequestBuffer.request(() ->
        new MessageBuilder(chat.getClient()).withChannel(user.getOrCreatePMChannel()).withContent(message).build());
    }

    public static void updateDeck(IDuelingBot chat, IUser user, Deck deck) {

        EmbedBuilder builder = new EmbedBuilder();
        builder.withColor(110, 150, 190);
        builder.appendField("Deck Owner", user.getName(), true);
        builder.appendField("Deck Size", String.valueOf(deck.getDeck().size()), true);
        if (!deck.getDeck().isEmpty()) {
            for (int i = 0; i < deck.getDeck().size(); i++) {
                ICard card = deck.getDeck().get(i);
                builder.appendDesc(i + ":" + "\t" + card.getCardName() + "\n");
            }
        }
        else if (deck.getDeck().isEmpty()) {
            builder.appendDesc("Your deck is empty. Nothing to see here!");
        }
        RequestBuffer.request(() ->
                user.getOrCreatePMChannel().sendMessage(builder.build()));
    }

    public static void updateField(IDuelingBot chat, IChannel channel, PlayingField pf) {

        EmbedBuilder builder = new EmbedBuilder();

        builder.withColor(110, 150, 190);
        builder.withTitle("Dueling Field");
        builder.appendField("Duelist", "Life Points", true);
        builder.appendField(pf.getOwner().getDisplayName(channel.getGuild()), String.valueOf(pf.getLifePoints()), true);

        RequestBuffer.request(() -> channel.sendFile(builder.build(), ImageUtil.getField(pf), "field.png"));
    }

    public static void showCard(IDuelingBot chat, IUser user, ICard card) {

        List<String> list = card.getCardInfo();
        EmbedBuilder builder = new EmbedBuilder();

        builder.withColor(110, 150, 190);
        builder.withTitle(list.get(0));
        for (int i = 1; i < list.size(); i++)
            builder.appendDesc(list.get(i) + "\n");

        RequestBuffer.request(() ->
                user.getOrCreatePMChannel().sendMessage(builder.build()));
    }
}
