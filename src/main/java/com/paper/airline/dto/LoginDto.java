package com.paper.airline.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginDto {
    private String userId;
    private String password;

    @Builder
    public LoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
