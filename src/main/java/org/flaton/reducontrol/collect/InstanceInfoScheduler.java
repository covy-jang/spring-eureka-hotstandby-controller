package org.flaton.reducontrol.collect;

import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class InstanceInfoScheduler {
    private final InstanceStatusContextSwitcher contextSwitcher;
    private final EurekaContextRegistry registry;

    @Scheduled(fixedDelayString = "${eureka.server.collect-time-Interval:1000}")
    private void collect(){
        List<Application> registeredApplications = registry.findRegisterApplications();
        registeredApplications.stream()
                .map(Application::getInstances)
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .map(instanceInfo -> InstanceInfoDto.builder()
                        .instanceId(instanceInfo.getInstanceId())
                        .appName(instanceInfo.getAppName())
                        .ipAddr(instanceInfo.getIPAddr())
                        .sid(instanceInfo.getSID())
                        .hostName(instanceInfo.getHostName())
                        .status(instanceInfo.getStatus())
                        .lastUpdatedTimestamp(LocalDateTime
                                .ofInstant(Instant.ofEpochMilli(instanceInfo.getLastUpdatedTimestamp()),
                                TimeZone.getDefault().toZoneId()))
                        .build())
                .forEach(contextSwitcher::subscribe);
    }
}
