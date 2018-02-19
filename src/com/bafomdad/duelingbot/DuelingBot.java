package com.bafomdad.duelingbot;

import com.bafomdad.duelingbot.api.IDuelingBot;
import com.bafomdad.duelingbot.commands.CommandManager;
import com.bafomdad.duelingbot.events.EventListener;
import com.bafomdad.duelingbot.internal.Duel;
import com.bafomdad.duelingbot.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

import java.io.File;

/**
 * Created by bafomdad on 12/26/2017.
 */
public class DuelingBot implements IDuelingBot {

    public static DuelingBot INSTANCE;
    public static Config config;
    public static Duel currentDuel;

    private IDiscordClient client;
    private CommandManager commandManager;

    public DuelingBot(IDiscordClient client) {

        this.client = client;
        this.commandManager = new CommandManager(this);
        this.currentDuel = new Duel();
    }

    public static void main(String[] args) throws Exception {

        config = JsonUtil.fromJson(new TypeToken<Config>(){}, new File("config.json"), new Config());
        INSTANCE = login();
    }

    public static DuelingBot login() {

        DuelingBot bot = null;
        ClientBuilder cb = new ClientBuilder();
        cb.withToken(config.getToken());
        try {
            IDiscordClient client = cb.login();
            EventDispatcher dispatcher = client.getDispatcher();
            dispatcher.registerListener(new EventListener());
            bot = new DuelingBot(client);
        } catch (DiscordException e) {
            System.err.println("Error occurred while logging in!");
            e.printStackTrace();
        }
        return bot;
    }

    @Override
    public IDiscordClient getClient() {

        return client;
    }

    public CommandManager getCommandManager() {

        return commandManager;
    }

    public Duel getCurrentDuel() {

        return currentDuel;
    }
}
