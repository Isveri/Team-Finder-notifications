package com.evi.teamfindernotifications.service;


import com.evi.teamfindernotifications.domain.CustomNotification;
import com.evi.teamfindernotifications.domain.GroupRoom;
import com.evi.teamfindernotifications.mapper.GroupRoomMapper;
import com.evi.teamfindernotifications.mapper.NotificationMapper;
import com.evi.teamfindernotifications.model.CustomNotificationDTO;
import com.evi.teamfindernotifications.repository.GroupRepository;
import com.evi.teamfindernotifications.repository.NotificationRepository;
import com.evi.teamfindernotifications.repository.UserRepository;
import com.evi.teamfindernotifications.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;

import static com.evi.teamfindernotifications.domain.CustomNotification.NotifType.REMOVED;
import static com.evi.teamfindernotifications.utils.UserDetailsHelper.getCurrentUser;


@RequiredArgsConstructor
@Service
public class SseServiceImpl implements SseService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    private final GroupRoomMapper groupRoomMapper;
    public static final Map<Long, SseEmitter> emitters = new HashMap<>();


    @Override
    public void sendSseEventToUser(CustomNotificationDTO customNotificationDTO, Long groupId, Long modifiedUserId) {
        List<Long> usersId = new ArrayList<>();

        GroupRoom groupRoom = groupRepository.findById(groupId).orElseThrow(null);
        groupRoom.getUsers().forEach((user -> {
                if(!Objects.equals(user.getId(), modifiedUserId)) {
                    usersId.add(user.getId());
                    customNotificationDTO.setGroupRoom(groupRoomMapper.mapGroupRoomToGroupNotifInfoDTO(groupRoom));
                    CustomNotification customNotification = notificationMapper.mapCustomNotificationDTOToCustomNotification(customNotificationDTO);
                    customNotification.setUser(user);
                    customNotification.setGroupRoom(groupRoom);
                    notificationRepository.save(customNotification);
                }
        }));
        usersId.forEach((id) -> {
                    sendMsgToEmitter(customNotificationDTO, id);
                }
        );
        if (modifiedUserId != null && REMOVED.equals(customNotificationDTO.getType())) {
            sendRemovedNotif(customNotificationDTO, groupRoom, modifiedUserId);
        }
    }

    private void sendRemovedNotif(CustomNotificationDTO customNotificationDTO, GroupRoom groupRoom, Long modifiedUserId) {
        customNotificationDTO.setRemovedUserId(modifiedUserId);
        customNotificationDTO.setGroupRoom(groupRoomMapper.mapGroupRoomToGroupNotifInfoDTO(groupRoom));
        CustomNotification customNotification = notificationMapper.mapCustomNotificationDTOToCustomNotification(customNotificationDTO);
        customNotification.setUser(userRepository.findById(modifiedUserId).orElseThrow());
        customNotification.setGroupRoom(groupRoom);
        notificationRepository.save(customNotification);
        sendMsgToEmitter(customNotificationDTO, modifiedUserId);
    }

    @Override
    public void sendSseFriendEvent(CustomNotificationDTO customNotificationDTO, Long userId) {
        sendMsgToEmitter(customNotificationDTO,userId);
    }

    @Override
    public SseEmitter createEmitter() {
        User user = getCurrentUser();
        Long userId = user.getId();
        SseEmitter emitter = new SseEmitter(7_200_000L);
        emitters.put(userId, emitter);
        emitter.onTimeout(emitter::complete);
        emitter.onCompletion(() -> emitters.remove(userId));
        return emitter;
    }


    private void sendMsgToEmitter(CustomNotificationDTO customNotificationDTO, Long id) {
        SseEmitter emitter = emitters.get(id);
        try {
            if (emitter != null) {
                emitter.send(customNotificationDTO);
            }
        } catch (IOException e) {
            emitter.complete();
            e.printStackTrace();
        }
    }
}
