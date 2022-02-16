package org.flaton.reducontrol.domain.repository;

import org.flaton.reducontrol.domain.dto.InstanceInfoDto;

public interface InstanceInfoSupport {
    void setActive(InstanceInfoDto instanceInfoDto);
    void setStandbyExceptOne(InstanceInfoDto instanceInfoDto);
    void setStandby(String appName);
}
