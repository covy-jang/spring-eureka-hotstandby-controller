package com.paper.airline.controller;

import com.paper.airline.dto.InstanceInfoDto;
import com.paper.airline.service.InstanceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
