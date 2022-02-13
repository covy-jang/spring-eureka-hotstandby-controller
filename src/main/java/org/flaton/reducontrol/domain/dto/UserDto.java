package org.flaton.reducontrol.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.flaton.reducontrol.domain.entity.User;
import org.flaton.reducontrol.domain.vo.YesNo;

import java.time.LocalDateTime;

@Getter
public class UserDto {
    private String userId;
    private String password;
    private YesNo useYn;
    private LocalDateTime modDate;
    private String modUserId;
    private LocalDateTime regDate;
    private String regUserId;

    @Builder
    public UserDto(String userId, String password,
                   YesNo useYn, LocalDateTime modDate, String modUserId,
                   LocalDateTime regDate, String regUserId) {
        this.userId = userId;
        this.password = password;
        this.useYn = useYn;
        this.modDate = modDate;
        this.modUserId = modUserId;
        this.regDate = regDate;
        this.regUserId = regUserId;
    }

    public static UserDto of(User user){
        return  UserDto.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .useYn(user.getUseYn())
                .modDate(user.getModDate())
                .modUserId(user.getModUserId())
                .regDate(user.getRegDate())
                .regUserId(user.getRegUserId())
                .build();
    }
}
