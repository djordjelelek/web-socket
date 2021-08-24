package com.websocket.wstutorial.config;

import com.websocket.wstutorial.security.UserHandshakeHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    // endpoint za registraciju - frontend se subskrajbuje
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/our-websocket")
                .setAllowedOrigins("localhost")
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS();
    }

    // Konfiguracija
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // putanja za dobijanje poruka
        registry.setApplicationDestinationPrefixes("/ws"); // putanja aplikacije
    }
}
