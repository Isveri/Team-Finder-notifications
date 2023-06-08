package com.evi.teamfindernotifications.service;


import com.evi.teamfindernotifications.model.CustomNotificationDTO;

import java.util.List;

public interface NotificationService {

    List<CustomNotificationDTO> getAllNotifications();
    void removeNotification(Long notifId);
}
