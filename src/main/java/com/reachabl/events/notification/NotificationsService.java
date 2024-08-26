package com.reachabl.events.notification;


import com.reachabl.models.notification.Notification;

import java.util.List;

public interface NotificationsService {

    Notification save(Notification notification);

    List<Notification> findByUserID();
}
