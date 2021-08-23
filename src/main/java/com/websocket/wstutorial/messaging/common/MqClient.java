package com.websocket.wstutorial.messaging.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;

public abstract class MqClient {

    protected final Channel channel;

    protected ObjectMapper objectMapper;

    protected static final Logger ibsLogger = LoggerFactory.getLogger("ibs.logger");

    public MqClient(MessageQueueConnection mqConnection) throws IOException {
        channel = mqConnection.getConnection().createChannel();
        objectMapper = new ObjectMapper();
    }

    /**
     * Send generic message to queue
     *
     * @param queueName - queue name
     * @param message   - message content
     */
    public void pushMessageInQueue(String queueName, Object message) {

        String messageJson;
        try {
            messageJson = objectMapper.writeValueAsString(message);
            channel.basicPublish("", queueName, null, messageJson.getBytes());
        } catch (IOException ioException) {
            throw new UncheckedIOException(ioException);
        }
        ibsLogger.debug("Sent in {} '" + messageJson + "'", queueName);
    }

}
