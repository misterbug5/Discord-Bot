package io.github.misterbug5.discordbot.listeners.commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;

public class Help implements ICommand {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Gives you info about a command";
    }

    @Override
    public String getUsage(String prefix) {
        return String.format("%shelp or %shelp [command]", prefix, prefix);
    }

    @Override
    public void execute(CommandContext context) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Help Command");
        if (context.getGuild() != null) {
            embed.setThumbnail(context.getGuild().getIconUrl())
            .appendDescription(String.format("Working on help you to have a nice day in %s", context.getGuild().getName()));
        }else{
            embed.setThumbnail(context.getBot().getSelfUser().getEffectiveAvatarUrl())
            .appendDescription(String.format("Working on %d servers with you", context.getUser().getMutualGuilds().size()));
        }
        String args = String.join(" ", context.getArgs());
        ArrayList<ICommand> commands = context.getCommands();
        if (args.isEmpty()) {
            commands.forEach(command -> {
                embed.addField(command.getName(), command.getHelp(), false);
            });
        } else {
            int fields = embed.getFields().size();
            commands.forEach(command -> {
                if (command.getName().equalsIgnoreCase(args)) {
                    embed.addField("Description", command.getHelp(), false);
                    embed.addField("Usage", command.getUsage(context.getPrefix()), false);
                }
            });
            if (fields == embed.getFields().size()) {
                embed.addField("Error", String.format("The command \"%s\" not exist", args), false);
            }
        }
        context.getChannel().sendMessageEmbeds(embed.build()).queue();
    }
    
}
