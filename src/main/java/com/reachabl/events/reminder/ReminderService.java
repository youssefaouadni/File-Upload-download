package com.reachabl.events.reminder;

import com.reachabl.events.events.send_reminder.SendReminderEvent;
import com.reachabl.models.profile.Profile;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;

@Service
public class ReminderService {

    private final TaskScheduler taskScheduler;
    private final ApplicationEventPublisher publisher;

    public ReminderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.initialize();
        this.taskScheduler = scheduler;
    }

    public void scheduleReminder(LocalDateTime eventTime, List<Profile> profiles, Duration reminder, ZoneId zoneId, String meetingTopic) {
        LocalDateTime reminderTime = eventTime.minus(reminder); // Directly subtract the Duration
        Date reminderDate = Date.from(reminderTime.atZone(zoneId).toInstant());

        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(() -> sendReminder(meetingTopic,profiles), reminderDate);
    }

    private void sendReminder(String meetingTopic,List<Profile> profiles) {
        CompletableFuture.runAsync(() -> publisher.publishEvent(new SendReminderEvent(this,profiles,meetingTopic)));
    }
}
