package com.reachabl.events.events.setup_reminder;

import com.reachabl.events.reminder.ReminderService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SetupReminderEventListener {

    private final ReminderService reminderService;

    public SetupReminderEventListener(ReminderService reminderService) {
        this.reminderService = reminderService;
    }


    @EventListener
    public void onSetupReminder(SetupReminderEvent event) {
        reminderService.scheduleReminder(event.getEventTime(), event.getProfiles(), event.getReminder(), event.getZoneId(),event.getMeetingTopic());
    }
}
