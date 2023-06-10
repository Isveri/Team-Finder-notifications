package com.evi.teamfindernotifications.messaging;

import com.evi.teamfindernotifications.domain.CustomNotification;
import com.evi.teamfindernotifications.messaging.model.Notification;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import com.evi.teamfindernotifications.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "notification", name = "service",havingValue = "kafka")
@Component
public class KafkaNotificationListener {

    private final SseService sseService;

    @KafkaListener(topics = "notifications.topic", groupId = "notifs")
    public void handleNotification(Notification notification){
    if(notification.getNotificationType()== Notification.NotificationType.FRIENDREQUEST){
        sseService.sendSseFriendEvent(CustomNotificationDTO.builder().type(CustomNotification.NotifType.FRIENDREQUEST).build(),notification.getUserId());
    }
    }

}
