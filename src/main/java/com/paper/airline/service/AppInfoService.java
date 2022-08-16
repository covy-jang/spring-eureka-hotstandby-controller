package com.paper.airline.service;

import com.paper.airline.dto.AppInfoDto;
import lombok.RequiredArgsConstructor;
import com.paper.airline.repository.AppInfoRepository;
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
