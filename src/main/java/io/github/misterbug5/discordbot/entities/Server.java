package io.github.misterbug5.discordbot.entities;

import java.util.ArrayList;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import net.dv8tion.jda.api.entities.Guild;

@Document(collection = "servers")
public class Server {
    @MongoId private String id;
    private String adminChannel;
    private String musicChannel;
    private String prefix;
    private String adminRole;
    private ArrayList<CustomCommand> customCommands;
    private ArrayList<Command> commandSettings;

    public Server(Guild guild) {
        this.id = guild.getId();
        this.adminChannel = "";
        this.musicChannel = "";
        this.prefix = ">";
        this.adminRole = "";
        this.customCommands = new ArrayList<CustomCommand>();
        this.commandSettings = new ArrayList<Command>();
    }

    public Server() {
        this.id = "";
        this.adminChannel = "";
        this.musicChannel = "";
        this.prefix = "";
        this.adminRole = "";
        this.customCommands = new ArrayList<CustomCommand>();
        this.commandSettings = new ArrayList<Command>();
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getAdminChannel() {
        return adminChannel;
    }
    
    public void setAdminChannel(String adminChannel) {
        this.adminChannel = adminChannel;
    }
    
    public String getMusicChannel() {
        return musicChannel;
    }
    
    public void setMusicChannel(String musicChannel) {
        this.musicChannel = musicChannel;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public ArrayList<CustomCommand> getCustomCommands() {
        return customCommands;
    }

    public void setCustomCommands(ArrayList<CustomCommand> customCommands) {
        this.customCommands = customCommands;
    }

    public ArrayList<Command> getCommandSettings() {
        return commandSettings;
    }

    public void setCommandSettings(ArrayList<Command> commandSettings) {
        this.commandSettings = commandSettings;
    }

}
