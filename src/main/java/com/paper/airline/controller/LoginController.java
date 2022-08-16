package com.paper.airline.controller;

import com.paper.airline.dto.JwtTokenDto;
import com.paper.airline.dto.LoginDto;
import com.paper.airline.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class LoginController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(
                        loginDto.getUserId(),
                        loginDto.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(token);

        String accessToken = jwtTokenProvider.createToken(authentication);
        JwtTokenDto jwtTokenDto = JwtTokenDto.builder()
                .accessToken(accessToken)
                .build();

        return ResponseEntity.ok()
                .header(JwtTokenProvider.TOKEN_HEADER_NAME, accessToken)
                .body(jwtTokenDto);
    }

}
