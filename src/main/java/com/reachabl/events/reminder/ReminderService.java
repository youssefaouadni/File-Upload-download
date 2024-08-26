package com.reachabl.events.reminder;

import com.reachabl.models.profile.Profile;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class ReminderService {

    private final TaskScheduler taskScheduler;

    public ReminderService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.initialize();
        this.taskScheduler = scheduler;
    }

    public void scheduleReminder(LocalDateTime eventTime, List<Profile> profiles, LocalTime reminder,ZoneId zoneId) {
        LocalDateTime reminderTime = eventTime.minusHours(reminder.getHour()).minusMinutes(reminder.getMinute());
        Date reminderDate = Date.from(reminderTime.atZone(zoneId).toInstant());

        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(() -> sendReminder(), reminderDate);
    }

    private void sendReminder() {

    }
}
