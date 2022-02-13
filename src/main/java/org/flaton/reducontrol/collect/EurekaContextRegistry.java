package org.flaton.reducontrol.collect;

import com.netflix.discovery.shared.Application;
import com.netflix.eureka.EurekaServerContextHolder;
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
}
