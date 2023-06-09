package com.evi.teamfindernotifications.kafka.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    public enum NotificationType{
        REMOVED,
        INFO,
        WARNING,
        FRIENDREQUEST,
        PRIVATE_MESSAGE,
    }

    private Long groupId;
    private Long userId;
    private NotificationType notificationType;
}
