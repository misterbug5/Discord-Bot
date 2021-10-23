package io.github.misterbug5.discordbot.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
public class User {
    @MongoId private String id;
    private String prefix;

    public User(){
        id = "";
        prefix = ">";
    }

    public User(net.dv8tion.jda.api.entities.User author) {
        id = author.getId();
        prefix = ">";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    
}
