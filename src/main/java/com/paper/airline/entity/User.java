package com.paper.airline.entity;

import com.paper.airline.vo.YesNo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "TN_CM_USER")
@Entity
public class User {
    @Id
    private String userId;
    private String password;
    @Enumerated(EnumType.STRING)
    private YesNo useYn;
    private LocalDateTime modDate;
    private String modUserId;
    private LocalDateTime regDate;
    private String regUserId;

    @Builder
    public User(String userId, String password, YesNo useYn,
                LocalDateTime modDate, String modUserId,
                LocalDateTime regDate, String regUserId) {
        this.userId = userId;
        this.password = password;
        this.useYn = useYn;
        this.modDate = modDate;
        this.modUserId = modUserId;
        this.regDate = regDate;
        this.regUserId = regUserId;
    }
}
