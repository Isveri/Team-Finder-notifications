package com.evi.teamfindernotifications.kafka;

import com.evi.teamfindernotifications.domain.CustomNotification;
import com.evi.teamfindernotifications.kafka.model.Notification;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import com.evi.teamfindernotifications.service.NotificationService;
import com.evi.teamfindernotifications.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class NotificationListener {

    private final SseService sseService;

    @KafkaListener(topics = "notifications.topic", groupId = "notifs")
    public void handleNotification(Notification notification){
    if(notification.getNotificationType()== Notification.NotificationType.FRIENDREQUEST){
        sseService.sendSseFriendEvent(CustomNotificationDTO.builder().type(CustomNotification.NotifType.FRIENDREQUEST).build(),notification.getUserId());
    }
    }

}
