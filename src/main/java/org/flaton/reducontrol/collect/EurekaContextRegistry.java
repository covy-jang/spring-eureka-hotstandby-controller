package org.flaton.reducontrol.collect;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContextHolder;
import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EurekaContextRegistry {
    public List<Application> findRegisterApplications() {
        return EurekaServerContextHolder.getInstance().getServerContext()
                .getRegistry()
                .getApplications()
                .getRegisteredApplications();
    }

    public boolean contains(InstanceInfoDto instanceInfoDto) {
        InstanceInfo instanceInfo = EurekaServerContextHolder.getInstance().getServerContext()
                .getRegistry()
                .getApplication(instanceInfoDto.getAppName())
                .getByInstanceId(instanceInfoDto.getInstanceId());
        return (instanceInfo != null) ? true : false;
    }
}
