package com.paper.airline.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import com.paper.airline.entity.AppInfo;

@Setter
@Getter
public class AppInfoDto {
    private String appName;

    @Builder
    public AppInfoDto(String appName) {
        this.appName = appName;
    }

    public static AppInfoDto of(AppInfo appInfo) {
        return AppInfoDto.builder()
                .appName(appInfo.getAppName())
                .build();
    }

    public AppInfo toEntity(){
        return AppInfo.builder()
                .appName(appName)
                .build();
    }
}
