package com.websocket.wstutorial.service;

import com.websocket.wstutorial.messaging.TestMqClient;
import com.websocket.wstutorial.model.ResponseMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PushToRabbitService {

    private final SimpMessagingTemplate messagingTemplate;
    private final TestMqClient testMqClient;
    private final String queueName = "WebSocketTest";

    public PushToRabbitService(SimpMessagingTemplate messagingTemplate, TestMqClient testMqClient) {
        this.messagingTemplate = messagingTemplate;
        this.testMqClient = testMqClient;
    }

    // Rabbit Mq
    public void pushToRabbit(final String message) {
        ResponseMessage response = new ResponseMessage(message);
        testMqClient.pushMessageInQueue(queueName, response);
    }
}
