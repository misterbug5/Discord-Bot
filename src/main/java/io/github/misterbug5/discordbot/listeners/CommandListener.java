package io.github.misterbug5.discordbot.listeners;

import org.springframework.data.mongodb.core.MongoTemplate;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    private MongoTemplate database;

    public CommandListener(MongoTemplate database) {
        this.database = database;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isSystem()) {
            return;
        }
        event.getChannel().sendMessage("Hello World!").queue();
    }
}
