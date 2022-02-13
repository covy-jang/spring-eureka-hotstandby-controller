package org.flaton.reducontrol.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flaton.reducontrol.domain.dto.UserDto;
import org.flaton.reducontrol.domain.entity.User;
import org.flaton.reducontrol.domain.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId)
            throws UsernameNotFoundException {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId));
        return new org.springframework.security.core.userdetails.User(
                user.getUserId(),
                user.getPassword(),
                Set.of(new SimpleGrantedAuthority("USER"))
        );
    }

    public List<UserDto> getUserList(){
        return userRepository.findAll()
                .stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    public UserDto getUser(String userId){
        return UserDto.of(userRepository
                .findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId)));
    }

}
