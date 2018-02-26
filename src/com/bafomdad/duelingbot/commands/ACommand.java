package com.bafomdad.duelingbot.commands;

import com.bafomdad.duelingbot.api.ICommand;

/**
 * Created by bafomdad on 2/17/2018.
 */
public abstract class ACommand implements ICommand {

    @Override
    public boolean deleteCommandMessage() {

        return false;
    }
}
