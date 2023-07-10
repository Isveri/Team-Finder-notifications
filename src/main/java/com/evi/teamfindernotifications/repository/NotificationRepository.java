package com.evi.teamfindernotifications.repository;

import com.evi.teamfindernotifications.domain.CustomNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<CustomNotification, Long> {

    List<CustomNotification> findAllByUserId(Long userId);
}
