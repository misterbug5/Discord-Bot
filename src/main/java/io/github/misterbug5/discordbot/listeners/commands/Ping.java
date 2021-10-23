package io.github.misterbug5.discordbot.listeners.commands;

public class Ping implements ICommand {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getHelp() {
        return "Shows the ping of the bot";
    }

    @Override
    public String getUsage(String prefix) {
        return prefix + "ping";
    }

    @Override
    public void execute(CommandContext context) {
        context.getChannel().sendMessage("Pong!").queue(message->{
            message.editMessageFormat("%d", System.currentTimeMillis()-context.getTime()).queue();
        });
    }
    
}
