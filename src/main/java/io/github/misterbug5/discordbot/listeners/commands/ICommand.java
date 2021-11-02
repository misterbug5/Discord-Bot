package io.github.misterbug5.discordbot.listeners.commands;

public interface ICommand {
    public String getName();
    public String getHelp();
    public String getUsage(String prefix);
    public void execute(CommandContext context);
    public default void admin(CommandContext context){
        execute(context);
    }
}
