package org.flaton.reducontrol.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorDto {
    private final int status;
    private final String message;

    @Builder
    public ErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
