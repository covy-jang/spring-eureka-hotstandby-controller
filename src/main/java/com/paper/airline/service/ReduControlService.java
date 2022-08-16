package com.paper.airline.service;

import com.netflix.appinfo.InstanceInfo;
import com.paper.airline.config.InstanceStatusContextSwitcher;
import com.paper.airline.dto.AppInfoDto;
import com.paper.airline.dto.InstanceInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReduControlService {
    private final AppInfoService appInfoService;
    private final InstanceStatusContextSwitcher contextSwitcher;
    private final InstanceInfoService instanceInfoService;

    private Optional<InstanceInfoDto> electActiveInstance(String appName) {
        List<InstanceInfoDto> instanceInfoDtos = instanceInfoService
                .findInstanceInfoByAppName(appName);
        Optional<InstanceInfoDto> activeInstance = instanceInfoDtos.stream()
                .filter(e -> e.getStatus() == InstanceInfo.InstanceStatus.UP)
                .findFirst();
        return activeInstance;
    }

    @Scheduled(fixedDelayString = "${redu.elect-time-interval:1000}")
    public void updateActiveInstance() {
        List<AppInfoDto> appInfoDtos = appInfoService.findAppInfos();
        for (AppInfoDto appInfoDto : appInfoDtos) {
            Optional<InstanceInfoDto> activeInstance = electActiveInstance(appInfoDto.getAppName());
            activeInstance.ifPresentOrElse(
                    instanceInfoDto -> instanceInfoService.setRedundancy(instanceInfoDto),
                    () -> instanceInfoService.setRedundancyForFail(appInfoDto.getAppName())
            );
        }
    }

    @Scheduled(fixedDelayString = "${redu.sync-time-interval:10000}")
    public void syncUpInstance(){
        List<AppInfoDto> appInfoDtos = appInfoService.findAppInfos();
        for (AppInfoDto appInfoDto : appInfoDtos) {
            List<InstanceInfoDto> instanceInfoDtos = instanceInfoService
                    .findInstanceInfoByAppName(appInfoDto.getAppName());
            for(InstanceInfoDto instanceInfoDto : instanceInfoDtos){
                if(!contextSwitcher.containInstanceInfo(instanceInfoDto)){
                    instanceInfoService.deleteInstanceInfo(instanceInfoDto);
                }
            }
        }
    }
}
