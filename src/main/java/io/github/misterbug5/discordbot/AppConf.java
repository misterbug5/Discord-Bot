package io.github.misterbug5.discordbot;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class AppConf {
    public @Bean MongoClient mongoClient(@Autowired Dotenv env) {
        return MongoClients.create(env.get("MONGO_URL"));
    }

    public @Bean MongoTemplate mongoTemplate(@Autowired Dotenv env) {
        return new MongoTemplate(mongoClient(env), "discord");
    }
}
