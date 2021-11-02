package io.github.misterbug5.discordbot.listeners;

import java.util.ArrayList;
import java.util.Arrays;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.github.misterbug5.discordbot.entities.Server;
import io.github.misterbug5.discordbot.entities.User;
import io.github.misterbug5.discordbot.listeners.commands.Admin;
import io.github.misterbug5.discordbot.listeners.commands.CommandContext;
import io.github.misterbug5.discordbot.listeners.commands.Help;
import io.github.misterbug5.discordbot.listeners.commands.ICommand;
import io.github.misterbug5.discordbot.listeners.commands.Ping;
import io.github.misterbug5.discordbot.listeners.commands.Prefix;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
    private MongoTemplate database;
    private ArrayList<ICommand> guildCommands;
    private ArrayList<ICommand> privateCommands;
    private String prefix;
    private User privateUser;
    
    //private static final Logger LOGGER = LoggerFactory.getLogger(CommandListener.class);

    public CommandListener(MongoTemplate database) {
        this.database = database;
        this.guildCommands = new ArrayList<ICommand>();
        this.privateCommands = new ArrayList<ICommand>();
        addCommands();
    }

    private void addCommands() {
        this.guildCommands.add(new Ping());
        this.privateCommands.add(new Ping());
        this.guildCommands.add(new Help());
        this.privateCommands.add(new Help());
        this.guildCommands.add(new Prefix(database));
        this.privateCommands.add(new Prefix(database));
        this.guildCommands.add(new Admin());
        this.privateCommands.add(new Admin());
    }

    private void setCommands(Guild guild) {
        Server server = this.database.findById(guild.getId(), Server.class);
        if (server == null) {
            server = new Server(guild);
            this.database.insert(server);
        }
        this.prefix = server.getPrefix();
        //this.guildCommands.clear();
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
        CommandContext context = new CommandContext(args, guildCommands, event, this.prefix);
        this.guildCommands.forEach( command -> {
            if (command.getName().equalsIgnoreCase(commandString)) {
                command.execute(context);
            }
        });
    }

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.getAuthor().isSystem()) return;
        updateUser(event.getAuthor());

        if (!event.getMessage().getContentRaw().startsWith(this.privateUser.getPrefix())) return;

        String[] message = event.getMessage().getContentRaw().split(" ");
        String commandString = message[0].substring(this.privateUser.getPrefix().length()).toLowerCase();
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(message));
        args.remove(0);
        CommandContext context = new CommandContext(args, privateCommands, event, this.privateUser.getPrefix());
        this.privateCommands.forEach( command -> {
            if (command.getName().equalsIgnoreCase(commandString)) {
                command.execute(context);
            }
        });
    }

    private void updateUser(net.dv8tion.jda.api.entities.User author) {
        this.privateUser = database.findById(author.getId(), User.class);
        if (this.privateUser == null) {
            this.privateUser = new User(author);
            database.insert(this.privateUser);
        }
    }
    
}
