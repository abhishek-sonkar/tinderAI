package com.abhison.tinderAi;

import com.abhison.tinderAi.conversations.ChatMessage;
import com.abhison.tinderAi.conversations.Conversation;
import com.abhison.tinderAi.conversations.ConversationRepository;
import com.abhison.tinderAi.profiles.Gender;
import com.abhison.tinderAi.profiles.Profile;
import com.abhison.tinderAi.profiles.ProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiApplication implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(TinderAiApplication.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    public static void main(String[] args) {
        SpringApplication.run(TinderAiApplication.class, args);
    }

    public void run(String... args) {

        profileRepository.deleteAll();
        conversationRepository.deleteAll();

        Profile profile1 = new Profile("1", "Abhishek", "Sonkar", 29, Gender.MALE, "Indian", "Software Developer", "foo.jpg", "ESFP");
        profileRepository.save(profile1);

        Profile profile2 = new Profile("2", "Babu", "Bhaiya", 30, Gender.MALE, "Indian", "Software Developer", "foo.jpg", "ESFP");
        profileRepository.save(profile2);

        LOGGER.info(profileRepository.findAll());

        Conversation conversation = new Conversation("1", profile1.id(), List.of(new ChatMessage("Hello", profile1.id(), LocalDateTime.now())));

        conversationRepository.save(conversation);
        LOGGER.info(conversationRepository.findAll());
    }

}
