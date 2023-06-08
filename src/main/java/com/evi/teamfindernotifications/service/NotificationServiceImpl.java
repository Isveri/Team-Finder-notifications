package com.evi.teamfindernotifications.service;

import com.evi.teamfindernotifications.mapper.NotificationMapper;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import com.evi.teamfindernotifications.repository.NotificationRepository;
import com.evi.teamfindernotifications.utils.UserDetailsHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public List<CustomNotificationDTO> getAllNotifications() {
        return notificationRepository.findAllByUserId(UserDetailsHelper.getCurrentUser().getId())
                .stream()
                .map(notificationMapper::mapCustomNotificationToCustomNotificationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void removeNotification(Long notifId) {
        notificationRepository.deleteById(notifId);
    }
}
