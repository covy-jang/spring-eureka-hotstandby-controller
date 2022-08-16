package com.paper.airline.entity;

import com.netflix.appinfo.InstanceInfo.InstanceStatus;

import com.paper.airline.vo.Redundancy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "TN_CM_APP_INSTANCE")
@Entity
public class InstanceInfo {
    @Id
    private String instanceId;
    private String appName;
    private String ipAddr;
    private String sid;
    private String hostName;
    @Enumerated(EnumType.STRING)
    private InstanceStatus status;
    @Enumerated(EnumType.STRING)
    private Redundancy redundancy;
    private LocalDateTime lastUpdatedTimestamp;

    @Builder
    public InstanceInfo(String instanceId, String appName, String ipAddr,
                        String sid, String hostName, InstanceStatus status,
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

}
