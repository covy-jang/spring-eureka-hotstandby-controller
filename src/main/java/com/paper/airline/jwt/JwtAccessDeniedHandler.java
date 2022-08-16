package com.paper.airline.jwt;

import com.paper.airline.dto.ErrorDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //필요한 권한이 없이 접근하려 할때 403
        ErrorDto errorDto = ErrorDto.builder()
                .status(HttpServletResponse.SC_FORBIDDEN)
                .message("권한이 불충분합니다.")
                .build();
        response.getWriter()
                .println(errorDto);
    }
}

