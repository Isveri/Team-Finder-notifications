package com.evi.teamfindernotifications.messaging;

import com.evi.teamfindernotifications.domain.CustomNotification;
import com.evi.teamfindernotifications.messaging.model.Notification;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import com.evi.teamfindernotifications.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.evi.teamfindernotifications.utils.NotificationHelper.handleNotificationType;

@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "kafka")
@Component
public class KafkaNotificationListener {

    private final SseService sseService;


    @KafkaListener(topics = "notifications.topic", groupId = "notifs")
    public void handleNotification(Notification notification) {
        handleNotificationType(notification, sseService);
    }


}
