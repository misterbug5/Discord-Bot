package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

public class Command {
    private String name;
    private ArrayList<String> permsNeeded;
    private ArrayList<String> actions;

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
    
    public ArrayList<String> getActions() {
        return actions;
    }
    
    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }
    
}
