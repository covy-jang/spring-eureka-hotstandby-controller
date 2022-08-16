package com.paper.airline.dto;

import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JwtTokenDto {
    private String accessToken;

    @Builder
    public JwtTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
