package io.github.misterbug5.discordbot.listeners.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.github.misterbug5.discordbot.entities.Action;
import io.github.misterbug5.discordbot.entities.Command;
import io.github.misterbug5.discordbot.listeners.commands.actions.IAction;
import io.github.misterbug5.discordbot.listeners.commands.actions.Say;

public class Commands {
    private String name;
    private List<IAction> actions;

    public Commands(String name, List<IAction> actions) {
        this.name = name.toLowerCase();
        this.actions = actions;
    }

    public Commands(Command command) {
        this.name = command.getName();
        this.actions = new ArrayList<IAction>();
        for (Action action : command.getActions()) {
            switch (action.getAction()) {
                case SAY:
                    Say say = new Say();
                    say.setArgs((Arguments[]) action.getArgs().toArray());
                    break;
            
                default:
                    break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void execute(CommandContext context) {
        Iterator<IAction> iterator = actions.iterator();
        do {
            IAction action = iterator.next();
            action.handle(context);
        } while (iterator.hasNext());
    }
}
