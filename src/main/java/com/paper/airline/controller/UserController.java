package com.paper.airline.controller;

import com.paper.airline.dto.UserDto;
import com.paper.airline.jwt.JwtTokenProvider;
import com.paper.airline.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @GetMapping("/user")
    public UserDto getUser(HttpServletRequest request){
        String token = request.getHeader("accessToken");
        String userId = jwtTokenProvider.getUserId(token);
        return userService.getUser(userId);
    }

}
