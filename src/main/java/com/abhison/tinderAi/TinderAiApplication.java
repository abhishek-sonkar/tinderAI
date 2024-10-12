package com.abhison.tinderAi;

import com.abhison.tinderAi.profiles.Gender;
import com.abhison.tinderAi.profiles.Profile;
import com.abhison.tinderAi.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiApplication.class, args);
	}

	public void run(String... args) {
		Profile profile = new Profile("1", "Abhishek", "Sonkar", 29, Gender.MALE, "Indian", "Software Develope", "foo.jpg", "ESFP");
		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);
	}

}
