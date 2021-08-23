package com.websocket.wstutorial.messaging.common;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Component("AmqpConnection")
public class AmqpConnection implements MessageQueueConnection {

    @Value("${ibs.rabbitmq.username:}")
    private String rabbitUsername;

    @Value("${ibs.rabbitmq.password:}")
    private String rabbitPassword;

    @Value("${ibs.rabbitmq.url:}")
    private String rabbitUrl;

    @Getter
    private Connection connection;


    @PostConstruct
    public void createAmqpConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri("amqp://" + rabbitUsername + ":" + rabbitPassword + "@" + rabbitUrl);
            factory.setConnectionTimeout(300000);
            connection = factory.newConnection();
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException | TimeoutException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Connecting to message broker failed");
        }
    }

}
