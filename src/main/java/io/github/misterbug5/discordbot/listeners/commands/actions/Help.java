package io.github.misterbug5.discordbot.listeners.commands.actions;

import java.util.ArrayList;
import java.awt.Color;

import io.github.misterbug5.discordbot.listeners.commands.Arguments;
import io.github.misterbug5.discordbot.listeners.commands.Commands;
import net.dv8tion.jda.api.EmbedBuilder;

public class Help extends IAction {

    private String command;
    private boolean list;

    public Help() {
        this.command = "";
    }

    @Override
    protected void process() {
        if (this.context.getArgs().isEmpty()) {
            this.list = true;
            return;
        }
        this.list = false;
        this.command = this.context.getArgs().get(0);
    }

    @Override
    public void setArgs(Arguments... arg) {
        //This doesn't have args
    }

    @Override
    protected void execute() {
        ArrayList<Commands> commands = context.getCommands();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Help Command");
        embedBuilder.setColor(Color.getHSBColor(
            Color.RGBtoHSB(255, 85, 0, null)[0],
            Color.RGBtoHSB(255, 85, 0, null)[1],
            Color.RGBtoHSB(255, 85, 0, null)[2]
        ));
        embedBuilder.setThumbnail(context.getGuild().getIconUrl());
        if (list) {
            commands.forEach(command -> {
                embedBuilder.addField(command.getName(), command.getHelp(), false);
            });
        } else {
            boolean found = false;
            for (Commands command : commands) {
                if (command.getName().equalsIgnoreCase(this.command)) {
                    embedBuilder.addField(command.getName(), command.getUsage(), false);
                    found = true;
                }
            }
            if (!found) {
                embedBuilder.addField("Command not found", "Typed command not found\nMaybe not exist or you don't have the perms needed\nType help to see commands list", false);
            }
        }
        context.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
    }
    
}
