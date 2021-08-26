package com.websocket.wstutorial.controller;

import com.websocket.wstutorial.service.PushToRabbitService;
import com.websocket.wstutorial.service.WSService;
import com.websocket.wstutorial.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WSController {

    private final WSService service;
    private final PushToRabbitService pushToRabbitService;

    public WSController(WSService service, PushToRabbitService pushToRabbitService) {
        this.service = service;
        this.pushToRabbitService = pushToRabbitService;
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message) {
        service.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final Message message) {
        service.notifyUser(id, message.getMessageContent());
    }

    @PostMapping("/send-private-message")
    public void sendtoAll(@RequestBody final Message message) {
        service.notifyAll(message.getMessageContent());
    }

    // RabbitMq
    @PostMapping("/rabbit")
    public void rabbitMq(@RequestBody final Message message) {
        pushToRabbitService.pushToRabbit(message.getMessageContent());
    }
}
