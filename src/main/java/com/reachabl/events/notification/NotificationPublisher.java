package com.reachabl.events.notification;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface NotificationPublisher {

    @Gateway(requestChannel = "mqttOutboundChannel")
    void send(Message<String> message);
}
