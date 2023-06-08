package com.evi.teamfindernotifications.repository;

import com.evi.teamfindernotifications.domain.GroupRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupRepository extends JpaRepository<GroupRoom,Long>, JpaSpecificationExecutor<GroupRoom> {


}
