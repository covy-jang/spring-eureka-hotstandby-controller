package com.paper.airline.entity;

import com.paper.airline.vo.YesNo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "TN_CM_APP")
@Entity
public class AppInfo {
    @Id
    String appName;
    @Enumerated(EnumType.STRING)
    private YesNo useYn;
    private LocalDateTime modDate;
    private String modUserId;
    private LocalDateTime regDate;
    private String regUserId;

    @Builder
    public AppInfo(String appName, YesNo useYn, LocalDateTime modDate,
                   String modUserId, LocalDateTime regDate, String regUserId) {
        this.appName = appName;
        this.useYn = useYn;
        this.modDate = modDate;
        this.modUserId = modUserId;
        this.regDate = regDate;
        this.regUserId = regUserId;
    }
}
