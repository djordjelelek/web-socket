package com.websocket.wstutorial.messaging;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.DeliverCallback;
import com.websocket.wstutorial.messaging.common.MessageQueueConnection;
import com.websocket.wstutorial.messaging.common.MqClient;
import com.websocket.wstutorial.messaging.common.QueueNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RabbitMqServer extends MqClient {
    public RabbitMqServer(MessageQueueConnection mqConnection) throws IOException {
        super(mqConnection);
    }

//    private final WSRabbit wsRabbit;
//
//    private static final Logger ibsLogger = LoggerFactory.getLogger("ibs.logger");
//
//    @Autowired
//    public RabbitMqServer(@Qualifier("AmqpConnection") MessageQueueConnection amqpConnection,
//                          WSRabbit wsRabbit) throws IOException {
//        super(amqpConnection);
//        this.wsRabbit = wsRabbit;
//        this.subscribeForBofInfoRequests();
//    }
//
//    private void subscribeForBofInfoRequests() throws IOException {
//
//        channel.queueDeclare(QueueNames.BOF_INFO, true, false, false, null);
//
//        channel.basicQos(1);
//
//        ibsLogger.info("Awaiting requests for bof info");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            // Take basic properties from request
//            AMQP.BasicProperties replyProps = new AMQP.BasicProperties
//                    .Builder()
//                    .correlationId(delivery.getProperties().getCorrelationId())
//                    .build();
//
//            BofBetslipInfoResponseDto response = new BofBetslipInfoResponseDto();
//
//            try {
//                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//                ibsLogger.debug("Request for {} - {}", delivery.getProperties().getCorrelationId(), message);
//                BofBetslipInfoRequestDto requestDto = objectMapper.readValue(message, BofBetslipInfoRequestDto.class);
//
//                response = this.bofInfoResponseBuilder.build(requestDto);
//
//                ibsLogger.debug("Response for {} {}", delivery.getProperties().getCorrelationId(), response);
//            } catch (RuntimeException e) {
//                ibsLogger.warn("RuntimeException in fetching bof info " + e);
//            } finally {
//                String responseString = objectMapper.writeValueAsString(response);
//                channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, responseString.getBytes(StandardCharsets.UTF_8));
//                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//            }
//        };
//        channel.basicConsume(QueueNames.BOF_INFO, false, deliverCallback, (consumerTag -> {
//        }));
//    }
}