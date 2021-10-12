package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

import io.github.misterbug5.discordbot.listeners.commands.Actions;
import io.github.misterbug5.discordbot.listeners.commands.Arguments;

public class Command {
    private String name;
    private ArrayList<String> permsNeeded;
    private ArrayList<Action> actions;

    public Command() {
    }

    public Command(String name, ArrayList<String> permsNeeded) {
        this.name = name;
        this.permsNeeded = permsNeeded;
        this.actions = new ArrayList<Action>();
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<String> getPermsNeeded() {
        return permsNeeded;
    }
    
    public void setPermsNeeded(ArrayList<String> permsNeeded) {
        this.permsNeeded = permsNeeded;
    }
    
    public ArrayList<Action> getActions() {
        return actions;
    }
    
    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void addAction(Actions action, Arguments... args) {
        if (actions == null) {
            actions = new ArrayList<Action>();
        }
        actions.add(new Action(action, args));
    }
    
}
