package org.flaton.reducontrol.controller;

import lombok.RequiredArgsConstructor;
import org.flaton.reducontrol.domain.dto.AppInfoDto;
import org.flaton.reducontrol.service.AppInfoService;
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
