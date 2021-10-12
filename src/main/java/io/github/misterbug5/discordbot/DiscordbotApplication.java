package io.github.misterbug5.discordbot;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.misterbug5.discordbot.listeners.GuildJoinedListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@SpringBootApplication
public class DiscordbotApplication {

    @Autowired
    private MongoTemplate database;

	public static void main(String[] args) {
		SpringApplication.run(DiscordbotApplication.class, args);
	}

	@Bean
	public JDA bot(@Autowired Dotenv env) throws LoginException{
		return JDABuilder.createDefault(env.get("DISCORD_TOKEN"))
		.addEventListeners(new GuildJoinedListener(database))
		.build();
	}

	@Bean
	public Dotenv dotenv(){
		return Dotenv.configure().ignoreIfMissing().load();
	}

}
