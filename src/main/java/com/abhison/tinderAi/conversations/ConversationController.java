package com.abhison.tinderAi.conversations;

import com.abhison.tinderAi.profiles.ProfileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ConversationController {

    private static final Logger LOGGER = LogManager.getLogger(ConversationController.class);

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    public ConversationController(ConversationRepository conversationRepository, ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/conversations")
    public Conversation createNewConversation(@RequestBody CreateConversationRequest request) {

        profileRepository.findById(request.profileId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find profile with ID: " + request.profileId()));

        Conversation conversation = new Conversation(UUID.randomUUID().toString(), request.profileId, new ArrayList<>());

        conversationRepository.save(conversation);
        LOGGER.info(conversationRepository.findAll());
        return conversation;
    }

    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessage chatMessage) {

        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find conversation with ID: " + conversationId));

        profileRepository.findById(chatMessage.authorId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find profile with ID: " + chatMessage.authorId()));

        conversation.messages().add(new ChatMessage(chatMessage.messageText(), chatMessage.authorId(), LocalDateTime.now()));
        LOGGER.info(conversation);
        conversationRepository.save(conversation);
        return conversation;
    }

    public record CreateConversationRequest(String profileId) {
    }
}
