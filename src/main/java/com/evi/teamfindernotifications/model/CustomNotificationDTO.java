package com.evi.teamfindernotifications.model;

import com.evi.teamfindernotifications.domain.CustomNotification;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomNotificationDTO {

    private Long id;
    private String msg;
    private CustomNotification.NotifType type;
    private GroupNotifInfoDTO groupRoom;
    private Long removedUserId;
}
