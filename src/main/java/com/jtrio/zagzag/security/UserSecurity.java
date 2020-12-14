package com.jtrio.zagzag.security;

import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.user.UserCommand;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserSecurity {
    private final UserRepository userRepository;

    public User isUser(UserCommand.CheckUser checkUser){
        return userRepository.findByEmail(checkUser.getEmail()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

}
