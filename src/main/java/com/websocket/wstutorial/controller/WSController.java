package com.websocket.wstutorial.controller;

import com.websocket.wstutorial.service.WSService;
import com.websocket.wstutorial.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WSController {

    @Autowired
    private WSService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message) {
        service.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final Message message) {
        service.notifyUser(id, message.getMessageContent());
    }
}
