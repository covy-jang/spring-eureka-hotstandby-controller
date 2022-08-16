package com.paper.airline.controller;

import com.paper.airline.dto.AppInfoDto;
import com.paper.airline.service.AppInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appInfo")
@RequiredArgsConstructor
public class AppInfoController {
    private final AppInfoService appInfoService;

    @GetMapping("/")
    public List<AppInfoDto> getAppInfos(){
        return appInfoService.findAppInfos();
    }

}
