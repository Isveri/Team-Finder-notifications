package com.evi.teamfindernotifications.mapper;


import com.evi.teamfindernotifications.domain.GroupRoom;
import com.evi.teamfindernotifications.model.GroupNotifInfoDTO;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(builder = @Builder(disableBuilder = true),

        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class GroupRoomMapper {

    public abstract GroupNotifInfoDTO mapGroupRoomToGroupNotifInfoDTO(GroupRoom groupRoom);


}
