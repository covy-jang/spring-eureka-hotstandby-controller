package com.paper.airline.repository;

import com.paper.airline.dto.InstanceInfoDto;
import com.paper.airline.entity.InstanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstanceInfoRepository extends JpaRepository<InstanceInfo, String>, InstanceInfoSupport {
    List<InstanceInfo> findByAppName(String appName);
    void setActive(InstanceInfoDto instanceInfoDto);
    void setStandby(String appName);
    void setStandbyExceptOne(InstanceInfoDto instanceInfoDto);
}
