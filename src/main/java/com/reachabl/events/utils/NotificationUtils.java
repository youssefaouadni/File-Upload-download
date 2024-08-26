package com.reachabl.events.utils;

import com.reachabl.models.notification.Notification;
import com.reachabl.models.notification.NotificationContext;
import com.reachabl.models.profile.Profile;

import java.util.UUID;

public class NotificationUtils {
    public static Notification getNotification(Profile originUser, Profile destinationUser, String message) {
        Notification notification = new Notification();
        notification.setContext(NotificationContext.REMINDER);
        notification.setId(String.valueOf(UUID.randomUUID()));
        notification.setMessage(  message);
        notification.setReceiver(destinationUser);
        notification.setSender(originUser);
        return notification;
    }
}

