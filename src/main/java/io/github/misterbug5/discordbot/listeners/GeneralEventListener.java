package io.github.misterbug5.discordbot.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GeneralEventListener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralEventListener.class);

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        LOGGER.info("I Joined in " + event.getGuild().getId());
        super.onGuildJoin(event);
    }
}
