package org.flaton.reducontrol.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flaton.reducontrol.domain.dto.UserDto;
import org.flaton.reducontrol.jwt.JwtTokenProvider;
import org.flaton.reducontrol.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
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
