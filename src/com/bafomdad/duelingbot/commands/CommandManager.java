package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.api.ICommand;
import com.bafomdad.duelingbot.api.IDuelingBot;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.util.*;

/**
 * Created by bafomdad on 1/15/2018.
 */
public class CommandManager {

    private IDuelingBot client;
    private Map<String, ICommand> commands = new HashMap();

    public CommandManager(IDuelingBot client) {

        this.client = client;
        register(new CommandShutdown());
        register(new CommandDuel());
        register(new CommandHand());
        register(new CommandField());
        register(new CommandDraw());
        register(new CommandEdit());
        register(new CommandPlay());
    }

    public void register(ICommand command) {

        commands.put(command.getName().toLowerCase(), command);
    }

    public boolean exists(String name) {

        return commands.containsKey(name.toLowerCase());
    }

    public ICommand get(String name) {

        return commands.get(name.toLowerCase());
    }

    public Collection<ICommand> getCommands() {

        return commands.values();
    }

    public boolean hasPermission(ICommand command, IUser user, IGuild guild) {

        if (command.getMinimumPermission() == null) return true;

        EnumSet<Permissions> perms = user.getPermissionsForGuild(guild);
        if (perms == null || perms.isEmpty()) return false;

        return perms.contains(command.getMinimumPermission());
    }
}
