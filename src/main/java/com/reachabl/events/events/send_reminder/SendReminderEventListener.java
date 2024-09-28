package com.reachabl.events.events.send_reminder;

import com.reachabl.events.notification.NotificationPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendReminderEventListener {

    private final NotificationPublisher publisher;

    public SendReminderEventListener(NotificationPublisher publisher) {
        this.publisher = publisher;
    }

    @EventListener
    public void onSendReminderEvent(SendReminderEvent event) {
       event.getProfiles().forEach(profile -> {
           Message<String> message = new GenericMessage<>(
                   event.getMeetingTopic(),
                   Map.of(MqttHeaders.TOPIC, "/meeting/%s".formatted(profile.getId())));
           publisher.send(message);
       });
    }
}
