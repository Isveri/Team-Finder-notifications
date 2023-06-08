package com.evi.teamfindernotifications.service;

import com.evi.teamfindernotifications.domain.GroupRoom;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {
    void sendSseEventToUser(CustomNotificationDTO customNotificationDTO, Long groupId, Long modifiedUserId);

    void sendSseFriendEvent(CustomNotificationDTO customNotificationDTO, Long userId);

    SseEmitter createEmitter();

}
