package org.flaton.reducontrol.domain.repository;

import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.flaton.reducontrol.domain.entity.InstanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstanceInfoRepository extends JpaRepository<InstanceInfo, String>, InstanceInfoSupport {
    List<InstanceInfo> findByAppName(String appName);
    void setActive(InstanceInfoDto instanceInfoDto);
    void setStandby(String appName);
    void setStandbyExceptOne(InstanceInfoDto instanceInfoDto);
}
