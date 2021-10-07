package io.github.misterbug5.discordbot.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class GeneralEventListener implements EventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralEventListener.class);

    @Override
    public void onEvent(GenericEvent arg0) {
        LOGGER.info(arg0.toString());
    }
    
}
