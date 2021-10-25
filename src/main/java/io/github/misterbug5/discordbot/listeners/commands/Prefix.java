package io.github.misterbug5.discordbot.listeners.commands;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import io.github.misterbug5.discordbot.entities.Server;
import io.github.misterbug5.discordbot.entities.User;

public class Prefix implements ICommand {
    private MongoTemplate database;

    public Prefix(MongoTemplate database) {
        this.database = database;
    }

    @Override
    public String getName() {
        return "prefix";
    }

    @Override
    public String getHelp() {
        return "Sets your prefix to a new one";
    }

    @Override
    public String getUsage(String prefix) {
        return String.format("%sprefix [new prefix (without spaces)]", prefix);
    }

    @Override
    public void execute(CommandContext context) {
        if (context.getArgs().size() != 1) {
            context.getChannel().sendMessage("You must put a valid prefix without spaces").queue();
            return;
        }
        String prefix = String.join(" ", context.getArgs());
        if (context.getGuild() != null) {
            database.findAndModify(
                Query.query(Criteria.where("_id").is(context.getGuild().getId())),
                new Update().set("prefix", prefix),
                Server.class);
        } else {
            database.findAndModify(
                Query.query(Criteria.where("_id").is(context.getGuild().getId())),
                new Update().set("prefix", prefix),
                User.class);
        }
        context.getChannel().sendMessageFormat("Prefix changed from %s to %s", context.getPrefix(), prefix).queue();
    }
    
}
