package com.websocket.wstutorial.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import com.websocket.wstutorial.messaging.common.MessageQueueConnection;
import com.websocket.wstutorial.messaging.common.MqClient;
import com.websocket.wstutorial.model.ResponseMessage;
import com.websocket.wstutorial.service.PickUpFromRabbit;
import com.websocket.wstutorial.service.WSService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TestMqClient extends MqClient {

    private final String queueName = "WebSocketTest";

    private final PickUpFromRabbit pickUpFromRabbit;

    public TestMqClient(MessageQueueConnection mqConnection, PickUpFromRabbit pickUpFromRabbit) throws IOException {
        super(mqConnection);
        this.pickUpFromRabbit = pickUpFromRabbit;
        startUpdating();
    }

    /***
     * Subscribe for messages
     * @throws IOException - IO exception with rabbitMQ
     */
    public void startUpdating() throws IOException {

        final Delivery[] deliveryTmp = {null};

        channel.queueDeclare(queueName, true, false, false, null);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                deliveryTmp[0] = delivery;

                ResponseMessage response  = objectMapper.readValue(message, ResponseMessage.class);

                pickUpFromRabbit.pickUpFromRabbit(response);
            };

            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
            });

        } catch (Exception ioException) {
            channel.basicReject(deliveryTmp[0].getEnvelope().getDeliveryTag(), true);
        }
    }

}