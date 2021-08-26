package com.websocket.wstutorial.service;

import com.websocket.wstutorial.model.ResponseMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PickUpFromRabbit {

    private final SimpMessagingTemplate messagingTemplate;

    public PickUpFromRabbit(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void pickUpFromRabbit(ResponseMessage response) {
        messagingTemplate.convertAndSend("/topic/messages", response);
    }
}
