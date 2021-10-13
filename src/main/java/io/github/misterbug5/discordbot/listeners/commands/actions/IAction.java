package io.github.misterbug5.discordbot.listeners.commands.actions;

import io.github.misterbug5.discordbot.listeners.commands.Arguments;
import io.github.misterbug5.discordbot.listeners.commands.CommandContext;

public abstract class IAction {
    protected CommandContext context;

    public IAction(){
    }
    
    public void handle(CommandContext context){
        this.context = context;
        process();
        execute();
    }

    protected abstract void process();

    public abstract void setArgs(Arguments arg);

    protected abstract void execute();

}
