package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.api.ICommand;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 2/20/2018.
 */
public abstract class ASubCommand implements ICommand {

    @Override
    public Permissions getMinimumPermission() {

        return null;
    }

    @Override
    public String toString() {

        return getName();
    }
}
