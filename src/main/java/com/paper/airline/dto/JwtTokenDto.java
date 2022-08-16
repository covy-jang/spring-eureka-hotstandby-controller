package com.paper.airline.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenDto {
    private String accessToken;

    @Builder
    public JwtTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
