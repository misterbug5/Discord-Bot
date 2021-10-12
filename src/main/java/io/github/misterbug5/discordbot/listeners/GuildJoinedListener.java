package io.github.misterbug5.discordbot.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.github.misterbug5.discordbot.entities.Server;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoinedListener extends ListenerAdapter {
    private MongoTemplate database;

    private static final Logger LOGGER = LoggerFactory.getLogger(GuildJoinedListener.class);

    public GuildJoinedListener(MongoTemplate database) {
        this.database = database;
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        LOGGER.info("I Joined in " + event.getGuild().getId());
        database.insert(new Server(event.getGuild()));
    }
}
