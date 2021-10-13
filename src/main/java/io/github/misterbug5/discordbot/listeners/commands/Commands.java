package io.github.misterbug5.discordbot.listeners.commands;

import java.util.Iterator;
import java.util.List;

import io.github.misterbug5.discordbot.listeners.commands.actions.IAction;

public class Commands {
    private String name;
    private List<IAction> actions;

    public Commands(String name, List<IAction> actions) {
        this.name = name.toLowerCase();
        this.actions = actions;
    }

    public String getName() {
        return name;
    }

    public void execute(CommandContext context) {
        Iterator<IAction> iterator = actions.iterator();
        do {
            IAction action = iterator.next();
            action.setArgs(Arguments.PING);
            action.handle(context);
        } while (iterator.hasNext());
    }
}
