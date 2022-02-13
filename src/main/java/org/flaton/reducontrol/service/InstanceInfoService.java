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
        return InstanceInfoDto.of(instanceInfoRepository.save(instanceInfoDto.toEntity()));
    }

    @Transactional
    public List<InstanceInfoDto> updateInstanceInfos(List<InstanceInfoDto> instanceInfoDtos){
        List<InstanceInfo> instanceInfos = instanceInfoDtos.stream()
                .map(instanceInfoDto -> instanceInfoDto.toEntity())
                .collect(Collectors.toList());
        return instanceInfoRepository.saveAll(instanceInfos)
                .stream()
                .map(InstanceInfoDto::of)
                .collect(Collectors.toList());
    }

    public List<InstanceInfoDto> findInstanceInfoByAppName(String appName) {
        return instanceInfoRepository.findByAppName(appName)
                .stream()
                .sorted(Comparator.comparing(InstanceInfo::getInstanceId))
                .map(InstanceInfoDto::of)
                .collect(Collectors.toList());
    }

}
