package com.reachabl.events.events.send_reminder;

import com.reachabl.models.profile.Profile;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class SendReminderEvent extends ApplicationEvent {
    private final List<Profile> profiles;
    private final String meetingTopic;
    public SendReminderEvent(Object source, List<Profile> profiles, String meetingTopic) {
        super(source);
        this.profiles = profiles;
        this.meetingTopic = meetingTopic;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
}
