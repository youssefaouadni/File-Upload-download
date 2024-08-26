package com.reachabl.events.notification.local;

import com.reachabl.events.notification.NotificationsService;
import com.reachabl.models.notification.Notification;
import org.springframework.stereotype.Service;
import com.reachabl.events.utils.UserUtils;
import java.util.List;

@Service
public class DefaultNotificationsService implements NotificationsService {

    private final NotificationRepository notificationRepository;

    public DefaultNotificationsService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> findByUserID() {
        return notificationRepository.findAll()
                .stream()
                .filter(notification -> notification.getReceiver().getUser().getId().equals(UserUtils.getUserIdFromToken())).toList();
    }
}
