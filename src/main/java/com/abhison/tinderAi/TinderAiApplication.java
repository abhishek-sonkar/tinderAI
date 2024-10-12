package com.abhison.tinderAi;

import com.abhison.tinderAi.conversations.ChatMessage;
import com.abhison.tinderAi.conversations.Conversation;
import com.abhison.tinderAi.conversations.ConversationRepository;
import com.abhison.tinderAi.profiles.Gender;
import com.abhison.tinderAi.profiles.Profile;
import com.abhison.tinderAi.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationRepository conversationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiApplication.class, args);
	}

	public void run(String... args) {
		Profile profile = new Profile("1", "Abhishek", "Sonkar", 29, Gender.MALE, "Indian", "Software Develope", "foo.jpg", "ESFP");
		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);

		Conversation conversation = new Conversation("1", profile.id(),
				List.of(new ChatMessage("Hello", profile.id(), LocalDateTime.now())));

		conversationRepository.save(conversation);
		conversationRepository.findAll().forEach(System.out::println);
	}

}
