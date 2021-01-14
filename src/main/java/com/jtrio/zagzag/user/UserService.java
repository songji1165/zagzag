package com.jtrio.zagzag.user;

import com.jtrio.zagzag.exception.DuplicateDataException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean findUserEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public UserDto joinUser(UserCommand.CreateUser command){
        User user = command.toUser();

        //중복 처리 만들기
//        User name = userRepository.findByUser(user.name);
        boolean duplicatedEmail = userRepository.existsByEmail(user.getEmail());

        if(duplicatedEmail){
            //throws는 다른이에게 위임
            //throw는 내가 예외내고 끝냄
            throw new DuplicateDataException("이미 존재하는 이메일입니다.");
        }

        user.setPass(passwordEncoder.encode(user.getPass()));
        User savedUser = userRepository.save(user);

        UserDto userDto = UserDto.toUserDto(savedUser);
        return userDto;
    }

    @Transactional
    public UserDto updateUser(SecurityUser securityUser, UserCommand.UpdateUser command){
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        userRepository.save(command.toUser(user));
        return UserDto.toUserDto(user);
    }

}
