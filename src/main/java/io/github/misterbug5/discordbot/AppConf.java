package io.github.misterbug5.discordbot;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy.RepositoryDetectionStrategies;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.misterbug5.discordbot.entities.Server;
import io.github.misterbug5.discordbot.entities.User;

@Configuration
public class AppConf {
    public @Bean MongoClient mongoClient(@Autowired Dotenv env) {
        return MongoClients.create(env.get("MONGO_URL"));
    }

    public @Bean MongoTemplate mongoTemplate(@Autowired Dotenv env) {
        MongoTemplate database = new MongoTemplate(mongoClient(env), "discord");
        String versionControlString = "version";
        database.update(User.class)
        .matching(Criteria.where(versionControlString).exists(false))
        .apply(
            Aggregation.newUpdate().set(versionControlString).toValue(0)
            ).all();
        database.update(Server.class)
        .matching(Criteria.where(versionControlString).exists(false))
        .apply(
            Aggregation.newUpdate().set(versionControlString).toValue(0)
            ).all();
        database.update(Server.class)
        .matching(Criteria.where(versionControlString).is(0))
        .apply(
            Aggregation.newUpdate().set("logChannel").toValue("")
            .set("notificationsChannel").toValue("")
            .set(versionControlString).toValue(1)
            ).all();
        return database;
    }

    public RepositoryDetectionStrategy rStrategy(){
        return RepositoryDetectionStrategies.ANNOTATED;
    }
}
