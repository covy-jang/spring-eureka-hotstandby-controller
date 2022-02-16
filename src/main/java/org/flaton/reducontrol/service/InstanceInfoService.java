package org.flaton.reducontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.flaton.reducontrol.domain.entity.InstanceInfo;
import org.flaton.reducontrol.domain.repository.InstanceInfoRepository;
import org.flaton.reducontrol.collect.InstanceStatusContextSwitcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class InstanceInfoService {
    private final InstanceInfoRepository instanceInfoRepository;
    private final InstanceStatusContextSwitcher contextSwitcher;

    @PostConstruct
    public void init(){
        contextSwitcher.registerEventListener(this::updateInstanceInfo);
    }

    @Transactional
    public InstanceInfoDto updateInstanceInfo(InstanceInfoDto instanceInfoDto){
        InstanceInfo instanceInfo = instanceInfoRepository.save(instanceInfoDto.toEntity());
        return InstanceInfoDto.of(instanceInfo);
    }

    @Transactional
    public void setRedundancy(InstanceInfoDto instanceInfoDto){
        instanceInfoRepository.setActive(instanceInfoDto);
        instanceInfoRepository.setStandbyExceptOne(instanceInfoDto);
    }

    @Transactional
    public void setRedundancyForFail(String appName){
        instanceInfoRepository.setStandby(appName);
    }

    public List<InstanceInfoDto> findInstanceInfoByAppName(String appName) {
        return instanceInfoRepository.findByAppName(appName)
                .stream()
                .sorted(Comparator.comparing(InstanceInfo::getInstanceId))
                .map(InstanceInfoDto::of)
                .collect(Collectors.toList());
    }

    public void deleteInstanceInfo(InstanceInfoDto instanceInfoDto){
        instanceInfoRepository.delete(instanceInfoDto.toEntity());
    }

}
