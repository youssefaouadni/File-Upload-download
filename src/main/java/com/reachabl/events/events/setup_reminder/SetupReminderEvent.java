package com.reachabl.events.events.setup_reminder;

import com.reachabl.models.profile.Profile;
import org.springframework.context.ApplicationEvent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

public class SetupReminderEvent extends ApplicationEvent {
    private final LocalDateTime eventTime;
    private final List<Profile> profiles;
    private final Duration reminder;
    private final ZoneId zoneId;
    private final String meetingTopic;

    public SetupReminderEvent(Object source, LocalDateTime eventTime, List<Profile> profiles, Duration reminder, ZoneId zoneId, String meetingTopic) {
        super(source);
        this.eventTime = eventTime;
        this.profiles = profiles;
        this.reminder = reminder;
        this.zoneId = zoneId;
        this.meetingTopic = meetingTopic;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Duration getReminder() {
        return reminder;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }
}
