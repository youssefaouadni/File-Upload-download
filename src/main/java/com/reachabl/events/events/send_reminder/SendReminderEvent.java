package com.reachabl.events.events.send_reminder;

import org.springframework.context.ApplicationEvent;

public class SendReminderEvent extends ApplicationEvent {
    public SendReminderEvent(Object source) {
        super(source);
    }
}
