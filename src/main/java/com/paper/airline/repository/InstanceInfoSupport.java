package com.paper.airline.repository;

import com.paper.airline.dto.InstanceInfoDto;

public interface InstanceInfoSupport {
    void setActive(InstanceInfoDto instanceInfoDto);
    void setStandbyExceptOne(InstanceInfoDto instanceInfoDto);
    void setStandby(String appName);
}
