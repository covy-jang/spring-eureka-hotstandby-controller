package com.paper.airline.schedule;

import com.netflix.discovery.shared.Application;
import com.paper.airline.config.EurekaContextRegistry;
import com.paper.airline.dto.AppInfoDto;
import com.paper.airline.service.AppInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class AppInfoScheduler {
    private final AppInfoService appInfoService;
    private final EurekaContextRegistry registry;

    @Scheduled(fixedDelayString = "${eureka.server.collect-time-Interval:1000}")
    private void collect(){
        List<Application> registeredApplications = registry.findRegisterApplications();
        registeredApplications.stream()
                .map(application -> AppInfoDto.builder()
                        .appName(application.getName())
                        .build())
                .forEach(appInfoDto -> appInfoService.updateAppInfo(appInfoDto));
    }
}
