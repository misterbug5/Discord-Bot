package io.github.misterbug5.discordbot.listeners.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.misterbug5.discordbot.entities.Action;
import io.github.misterbug5.discordbot.entities.Command;
import io.github.misterbug5.discordbot.listeners.commands.actions.IAction;
import io.github.misterbug5.discordbot.listeners.commands.actions.Say;

public class Commands {
    private String name;
    private List<IAction> actions;

    private static final Logger LOGGER = LoggerFactory.getLogger(Commands.class);

    public Commands(String name, List<IAction> actions) {
        this.name = name.toLowerCase();
        this.actions = actions;
    }

    public Commands(Command command) {
        this.name = command.getName();
        this.actions = new ArrayList<IAction>();
        for (Action action : command.getActions()) {
            Arguments[] args = new Arguments[action.getArgs().size()];
            args = action.getArgs().toArray(args);
            switch (action.getAction()) {
                case SAY:
                    Say say = new Say();
                    say.setArgs(args);
                    this.actions.add(say);
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
        try {
            do {
                IAction action = iterator.next();
                action.handle(context);
            } while (iterator.hasNext());
        } catch (NoSuchElementException e) {
            LOGGER.error("Command %s is empty", this.name);
        }
        
    }

    public String getHelp(){
        return "A command called " + getName();
    }

    public String getUsage(){
        return "Usage: " + getName();
    }
}
