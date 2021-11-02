package io.github.misterbug5.discordbot.listeners.commands;

import java.util.ArrayList;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Admin implements ICommand {

    //private static Logger log = LoggerFactory.getLogger(Admin.class);

    @Override
    public String getName() {
        return "admin";
    }

    @Override
    public String getHelp() {
        return "A prefix to use admin only command";
    }

    @Override
    public String getUsage(String prefix) {
        return String.format("%sadmin [command]", prefix);
    }

    @Override
    public void execute(CommandContext context) {
        ArrayList<String> args = context.getArgs();
        String command = args.get(0).replaceFirst(context.getPrefix(), "");
        args.remove(0);
        ArrayList<ICommand> commands = context.getCommands();
        if (hasAdmin(context)){
            boolean found = false;
            for (ICommand iCommand : commands) {
                if(!iCommand.getName().equalsIgnoreCase(command)){
                    continue;
                }
                found = true;
                iCommand.admin(context);
            }
            if (!found) {
                context.getChannel().sendMessageFormat("Command don't exist, make sure to don't use prefix twice or type %shelp", context.getPrefix()).queue();
            }
        } else {
            context.getChannel().sendMessage("You don't have the necessary perms to do this").queue();
        }
    }

    @Override
    public void admin(CommandContext context) {
        context.getChannel().sendMessage("You have admin perms").queue();
    }

    public boolean hasAdmin(CommandContext context){
        if (context.getGuild() == null) {
            return true;
        }
        boolean admin = false;
        if (context.getGuild().retrieveOwner().complete().getUser().getId().equals(context.getUser().getId())) {
            admin = true;
        }
        return admin;
    }
    
}
