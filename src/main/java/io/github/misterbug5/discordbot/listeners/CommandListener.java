package io.github.misterbug5.discordbot.listeners;

import java.util.ArrayList;
import java.util.Arrays;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.github.misterbug5.discordbot.entities.Server;
import io.github.misterbug5.discordbot.listeners.commands.CommandContext;
import io.github.misterbug5.discordbot.listeners.commands.ICommand;
import io.github.misterbug5.discordbot.listeners.commands.Ping;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    private MongoTemplate database;
    private ArrayList<ICommand> commands;
    private String prefix;
    
    //private static final Logger LOGGER = LoggerFactory.getLogger(CommandListener.class);

    public CommandListener(MongoTemplate database) {
        this.database = database;
        this.commands = new ArrayList<ICommand>();
        setCommands();
    }

    private void setCommands() {
        this.commands.add(new Ping());
    }

    private void setCommands(Guild guild) {
        Server server = this.database.findById(guild.getId(), Server.class);
        if (server == null) {
            server = new Server(guild);
            this.database.insert(server);
        }
        this.prefix = server.getPrefix();
        //this.commands.clear();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isSystem()) return;
        setCommands(event.getGuild());

        if (!event.getMessage().getContentRaw().startsWith(this.prefix)) return;

        executeCommand(event);
    }

    private void executeCommand(GuildMessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");
        String commandString = message[0].substring(prefix.length()).toLowerCase();
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(message));
        args.remove(0);
        CommandContext context = new CommandContext(args, commands, event);
        this.commands.forEach( command -> {
            if (command.getName().equalsIgnoreCase(commandString)) {
                command.execute(context);
            }
        });
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isSystem()) return;
        event.getChannel().sendMessage("Hello World").queue();
    }
    
}
