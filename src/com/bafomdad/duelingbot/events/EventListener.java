package com.bafomdad.duelingbot.events;

import com.bafomdad.duelingbot.DuelingBot;
import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.commands.CommandManager;
import com.bafomdad.duelingbot.internal.Duel;
import com.bafomdad.duelingbot.utils.MessageUtil;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by bafomdad on 1/15/2018.
 */
public class EventListener {

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {

        for (IChannel channel : event.getClient().getChannels()) {
            if (channel.getName().equals(DuelingBot.config.getChannel()))
                MessageUtil.send(DuelingBot.INSTANCE, channel, "Dueling bot online!");
        }
    }

    @EventSubscriber
    public void onAddReaction(ReactionAddEvent event) {

        //if (event.getReaction().getUsers().get(0).equals(event.getAuthor())) return;

        if (event.getChannel().getName().equals(DuelingBot.config.getChannel()) && event.getReaction().getEmoji().getName().equals("cardBack")) {
            if (event.getMessageID() == DuelingBot.INSTANCE.getDuelQueue()) {
                Duel currentDuel = DuelingBot.INSTANCE.getCurrentDuel();
                currentDuel.addPlayer(event.getUser());
                String player1 = (currentDuel.getPlayers()[0] != null) ? currentDuel.getPlayers()[0].getOwner().getName() : "[Empty]";
                String player2 = (currentDuel.getPlayers()[1] != null) ? currentDuel.getPlayers()[1].getOwner().getName() : "[Empty]";
                event.getMessage().edit("A duel has been queued up. Duelists: " + player1 + " / " + player2);
                if (currentDuel.canPlay() && currentDuel.canStartDuel()) {
                    currentDuel.startDuel();
                    MessageUtil.send(DuelingBot.INSTANCE, event.getChannel(), "The duel has started. Players: " + player1 + "/ " + player2);
                    event.getChannel().getMessageByID(DuelingBot.INSTANCE.getDuelQueue()).delete();
                    MessageUtil.send(DuelingBot.INSTANCE, event.getChannel(), currentDuel.getPlayingTurn().getOwner().getName() + " has first turn.");
                }
            }
        }
    }

    @EventSubscriber
    public void onRemoveReaction(ReactionRemoveEvent event) {

        if (!event.getReaction().getUsers().get(0).equals(event.getAuthor())) return;

        if (event.getChannel().getName().equals(DuelingBot.config.getChannel()) && event.getReaction().getEmoji().getName().equals("cardBack")) {
            if (event.getMessageID() == DuelingBot.INSTANCE.getDuelQueue()) {
                Duel currentDuel = DuelingBot.INSTANCE.getCurrentDuel();
                currentDuel.removePlayer(event.getUser());
                String player1 = (currentDuel.getPlayers()[0] != null) ? currentDuel.getPlayers()[0].getOwner().getName() : "[Empty]";
                String player2 = (currentDuel.getPlayers()[1] != null) ? currentDuel.getPlayers()[1].getOwner().getName() : "[Empty]";
                event.getMessage().edit("A duel has been queued up. Duelists: " + player1 + " / " + player2);
            }
        }
    }

    @EventSubscriber
    public void onMessage(MessageReceivedEvent event) {

        if (!event.getChannel().getName().equals(DuelingBot.config.getChannel())) {
            if (event.getChannel().getName().equals(event.getAuthor().getName())) {
                IMessage message = event.getMessage();
                if (message.getContent().startsWith("!")) {
                    CommandManager commands = DuelingBot.INSTANCE.getCommandManager();
                    String[] splitter = message.getContent().split(" ");
                    if (splitter[0].substring(1).equals("deckedit")) {
                        ICommand command = commands.get(splitter[0].substring(1));
                        command.execute(splitter, event.getAuthor(), event.getChannel());
                        return;
                    }
                }
            }
            return;
        }
        IMessage message = event.getMessage();
        if (message.getContent().startsWith("!")) {
            CommandManager commands = DuelingBot.INSTANCE.getCommandManager();
            String[] splitter = message.getContent().split(" ");
            if (commands.exists(splitter[0].substring(1))) {
                ICommand command = commands.get(splitter[0].substring(1));
                if (!commands.hasPermission(command, event.getAuthor(), event.getGuild())) return;

                command.execute(splitter, event.getAuthor(), event.getChannel());
                if (command.deleteCommandMessage())
                    message.delete();
                return;
            }
        }
    }
}
