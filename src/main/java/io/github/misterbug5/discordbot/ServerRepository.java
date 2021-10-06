package io.github.misterbug5.discordbot;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.github.misterbug5.discordbot.entities.Server;

@Repository
public interface ServerRepository extends MongoRepository<Server, Long> {
    
}
