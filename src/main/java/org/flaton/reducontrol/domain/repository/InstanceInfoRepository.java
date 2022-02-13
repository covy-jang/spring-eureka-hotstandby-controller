package org.flaton.reducontrol.domain.repository;

import org.flaton.reducontrol.domain.entity.InstanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstanceInfoRepository extends JpaRepository<InstanceInfo, String> {
    List<InstanceInfo> findByAppName(String appName);
}
