package com.websocket.wstutorial.messaging.common;

import com.rabbitmq.client.Connection;

public interface MessageQueueConnection {

    Connection getConnection();
}
