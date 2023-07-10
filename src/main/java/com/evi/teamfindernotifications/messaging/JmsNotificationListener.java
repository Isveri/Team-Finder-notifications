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

import static com.evi.teamfindernotifications.utils.NotificationHelper.handleNotificationType;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "activemq")
public class JmsNotificationListener {


    private final SseService sseService;

    @JmsListener(destination = "notifications")
    public void handleNotification(Notification notification) {

        handleNotificationType(notification, sseService);

    }
}
