package org.flaton.reducontrol.service;

import lombok.RequiredArgsConstructor;
import org.flaton.reducontrol.domain.dto.AppInfoDto;
import org.flaton.reducontrol.domain.entity.AppInfo;
import org.flaton.reducontrol.domain.repository.AppInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppInfoService {
    private final AppInfoRepository appInfoRepository;

    public List<AppInfoDto> findAppInfos() {
        return appInfoRepository.findAll()
                .stream()
                .map(AppInfoDto::of)
                .collect(Collectors.toList());
    }

    public AppInfoDto updateAppInfo(AppInfoDto appInfoDto){
        return AppInfoDto.of(appInfoRepository.save(appInfoDto.toEntity()));
    }

}
