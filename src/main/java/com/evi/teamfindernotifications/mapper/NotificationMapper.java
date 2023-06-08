package com.evi.teamfindernotifications.mapper;


import com.evi.teamfindernotifications.domain.CustomNotification;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(builder = @Builder(disableBuilder = true),
       // uses = {UserMapper.class, GroupRoomMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class NotificationMapper {

    public abstract CustomNotification mapCustomNotificationDTOToCustomNotification(CustomNotificationDTO customNotificationDTO);

    public abstract CustomNotificationDTO mapCustomNotificationToCustomNotificationDTO(CustomNotification customNotification);
}
