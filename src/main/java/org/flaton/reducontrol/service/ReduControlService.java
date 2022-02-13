package org.flaton.reducontrol.service;

import com.netflix.appinfo.InstanceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flaton.reducontrol.domain.dto.AppInfoDto;
import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.flaton.reducontrol.domain.vo.Redundancy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReduControlService {
    private final AppInfoService appInfoService;
    private final InstanceInfoService instanceInfoService;

    private Optional<InstanceInfoDto> determineActiveInstance(List<InstanceInfoDto> instanceInfoDtos){
        Optional<InstanceInfoDto> active = Optional.empty();
        for(InstanceInfoDto instanceInfoDto : instanceInfoDtos){
            if(instanceInfoDto.getStatus() == InstanceInfo.InstanceStatus.UP) {
                active = Optional.of(instanceInfoDto);
                return active;
            }
        }
        return active;
    }

    @Scheduled(fixedDelayString = "${redu.control-time-Interval:1000}")
    public void updateActiveInstance(){
        List<AppInfoDto> appInfoDtos = appInfoService.findAppInfos();
        for(AppInfoDto appInfoDto : appInfoDtos) {
            List<InstanceInfoDto> instanceInfoDtos = instanceInfoService
                    .findInstanceInfoByAppName(appInfoDto.getAppName());
            InstanceInfoDto active = determineActiveInstance(instanceInfoDtos)
                    .orElse(new InstanceInfoDto());
            for(InstanceInfoDto instanceInfoDto : instanceInfoDtos){
                if(instanceInfoDto.equals(active)){
                    instanceInfoDto.setRedundancy(Redundancy.ACTIVE);
                }else{
                    instanceInfoDto.setRedundancy(Redundancy.STANDBY);
                }
            }
            instanceInfoService.updateInstanceInfos(instanceInfoDtos);
        }
    }
}
