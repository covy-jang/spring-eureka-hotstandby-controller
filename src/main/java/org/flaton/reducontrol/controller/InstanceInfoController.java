package org.flaton.reducontrol.controller;

import lombok.RequiredArgsConstructor;
import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.flaton.reducontrol.service.InstanceInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/instanceInfo")
@RequiredArgsConstructor
public class InstanceInfoController {
    private final InstanceInfoService instanceInfoService;

    @GetMapping("/{appName}")
    public List<InstanceInfoDto> getInstanceInfos(@PathVariable String appName){
        return instanceInfoService.findInstanceInfoByAppName(appName);
    }
}
