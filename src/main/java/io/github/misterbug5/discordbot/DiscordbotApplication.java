package io.github.misterbug5.discordbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

@SpringBootApplication
public class DiscordbotApplication {

	public static void main(String[] args) throws IOException {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
						whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		//SpringApplication.run(DiscordbotApplication.class, args);
	}

	@Bean
	public JDA bot(@Autowired Dotenv env) throws LoginException{
		return JDABuilder.createDefault(env.get("DISCORD_TOKEN")).build();
	}

	@Bean
	public Dotenv dotenv(){
		return Dotenv.configure().ignoreIfMissing().load();
	}

}
