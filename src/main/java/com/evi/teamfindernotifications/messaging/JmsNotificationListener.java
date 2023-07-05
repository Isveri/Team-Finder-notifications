package com.evi.teamfindernotifications.messaging;

import com.evi.teamfindernotifications.domain.CustomNotification;
import com.evi.teamfindernotifications.messaging.model.Notification;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import com.evi.teamfindernotifications.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "notification", name = "service",havingValue = "activemq")
public class JmsNotificationListener {


    private final SseService sseService;

    @JmsListener(destination = "notifications")
    public void handleNotification(Notification notification) {
        if (notification.getNotificationType() == Notification.NotificationType.FRIENDREQUEST) {
            sseService.sendSseFriendEvent(CustomNotificationDTO.builder().type(CustomNotification.NotifType.FRIENDREQUEST).build(), notification.getUserId());
        }else if (notification.getNotificationType() == Notification.NotificationType.REMOVED) {
            sseService.sendSseEventToUser(CustomNotificationDTO.builder().msg(notification.getMsg()).type(CustomNotification.NotifType.REMOVED).build(), notification.getGroupId(), notification.getUserId());
        }else if(notification.getNotificationType() == Notification.NotificationType.INFO){
            sseService.sendSseEventToUser(CustomNotificationDTO.builder().msg(notification.getMsg()).type(CustomNotification.NotifType.INFO).build(), notification.getGroupId(), null);
        }else{
            sseService.sendSseFriendEvent(CustomNotificationDTO.builder().msg(notification.getMsg()).type(CustomNotification.NotifType.PRIVATE_MESSAGE).build(), notification.getUserId());
        }
    }
}
