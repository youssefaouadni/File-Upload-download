package com.reachabl.events.notification.local;


import com.reachabl.models.notification.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

interface NotificationRepository extends MongoRepository<Notification,String> {
}
