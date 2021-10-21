package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

import io.github.misterbug5.discordbot.listeners.commands.Actions;
import io.github.misterbug5.discordbot.listeners.commands.Arguments;

public class Command {
    private String name;
    private ArrayList<String> permsNeeded;
    private ArrayList<Action> actions;
    private String helpString;
    private String usageString;

    public Command() {
    }

    public Command(String name, ArrayList<String> permsNeeded, String helpString, String usageString) {
        this.name = name;
        this.permsNeeded = permsNeeded;
        this.actions = new ArrayList<Action>();
        this.helpString = helpString;
        this.usageString = usageString;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getHelpString(){
        return helpString;
    }

    public void setHelpString(String helpString){
        this.helpString = helpString;
    }

    public String getUsageString(){
        return usageString;
    }

    public void setUsageString(String usageString){
        this.usageString = usageString;
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
