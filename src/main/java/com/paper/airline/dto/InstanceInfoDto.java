package com.paper.airline.dto;

import lombok.*;
import com.paper.airline.entity.InstanceInfo;
import com.paper.airline.vo.Redundancy;

import java.time.LocalDateTime;
import java.util.Objects;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Setter
@Getter
public class InstanceInfoDto {
    private String instanceId;
    private String appName;
    private String ipAddr;
    private String sid;
    private String hostName;
    private com.netflix.appinfo.InstanceInfo.InstanceStatus status;
    private Redundancy redundancy;
    private LocalDateTime lastUpdatedTimestamp;

    @Builder
    public InstanceInfoDto(String instanceId, String appName, String ipAddr,
                           String sid, String hostName, com.netflix.appinfo.InstanceInfo.InstanceStatus status,
                           Redundancy redundancy, LocalDateTime lastUpdatedTimestamp) {
        this.instanceId = instanceId;
        this.appName = appName;
        this.ipAddr = ipAddr;
        this.sid = sid;
        this.hostName = hostName;
        this.status = status;
        this.redundancy = redundancy;
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public static InstanceInfoDto of(InstanceInfo instanceInfo){
        return InstanceInfoDto.builder()
                .instanceId(instanceInfo.getInstanceId())
                .appName(instanceInfo.getAppName())
                .ipAddr(instanceInfo.getIpAddr())
                .sid(instanceInfo.getSid())
                .hostName(instanceInfo.getHostName())
                .status(instanceInfo.getStatus())
                .redundancy(instanceInfo.getRedundancy())
                .lastUpdatedTimestamp(instanceInfo.getLastUpdatedTimestamp())
                .build();
    }

    public InstanceInfo toEntity(){
        return InstanceInfo.builder()
                .instanceId(instanceId)
                .appName(appName)
                .ipAddr(ipAddr)
                .sid(sid)
                .hostName(hostName)
                .status(status)
                .redundancy(redundancy)
                .lastUpdatedTimestamp(lastUpdatedTimestamp)
                .build();
    }
}
