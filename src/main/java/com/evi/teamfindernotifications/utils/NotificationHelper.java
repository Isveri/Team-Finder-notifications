package com.evi.teamfindernotifications.utils;

import com.evi.teamfindernotifications.domain.CustomNotification;
import com.evi.teamfindernotifications.messaging.model.Notification;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import com.evi.teamfindernotifications.service.SseService;

public class NotificationHelper {

    public static void handleNotificationType(Notification notification, SseService sseService) {
        switch(notification.getNotificationType()){
            case FRIENDREQUEST -> sseService.sendSseFriendEvent(CustomNotificationDTO.builder().type(CustomNotification.NotifType.FRIENDREQUEST).build(), notification.getUserId());
            case REMOVED -> sseService.sendSseEventToUser(CustomNotificationDTO.builder().msg(notification.getMsg()).type(CustomNotification.NotifType.REMOVED).build(), notification.getGroupId(), notification.getUserId());
            case INFO -> sseService.sendSseEventToUser(CustomNotificationDTO.builder().msg(notification.getMsg()).type(CustomNotification.NotifType.INFO).build(), notification.getGroupId(), null);
            default -> sseService.sendSseFriendEvent(CustomNotificationDTO.builder().msg(notification.getMsg()).type(CustomNotification.NotifType.PRIVATE_MESSAGE).build(), notification.getUserId());
        }
    }
}
