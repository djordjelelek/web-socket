package com.websocket.wstutorial.service;

import com.websocket.wstutorial.messaging.TestMqClient;
import com.websocket.wstutorial.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message) {
        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
    }

    public void notifyAll(final String message) {
        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSend("/topic/private-messages", response);
    }
}
