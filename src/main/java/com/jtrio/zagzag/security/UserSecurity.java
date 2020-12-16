package com.jtrio.zagzag.security;

import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.user.UserCommand;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserSecurity {
    private final UserRepository userRepository;

    /**
     *
     *  Repository는 Service에서만 접근하도록 한다.
     *
     *  임시 security를 위해 email값으로 해당 user가 존재하는지만 판단한다.
     *
    **/
    public User isUser(String userId){
        return userRepository.findByEmail(userId).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

}
