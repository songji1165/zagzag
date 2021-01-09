package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.ErrorMsg;
import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.UserRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserCommand {
    @Data
    public static class CreateUser {

        @Email(message = "이메일 형식을 확인해주세요.")
        private String email;
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String pass;
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;
        private Gender gender;
        private String addr;
        private UserRole role;


        public User toUser(){
            User user = new User();
            user.setEmail(email);
            user.setPass(pass);
            user.setPass(pass);
            user.setName(name);
            user.setGender(gender);
            user.setAddr(addr);
            user.setRole(role);
            return user;
        }
    }

    @Data
    public static class UpdateUser {
        private String name;
        private String addr;
        private String pass;

        public User toUser(User user){
            if(name != null){
                user.setName(name);
            }

            if(addr != null){
                user.setAddr(addr);
            }

            if(pass != null){
                user.setPass(pass);
            }

            return user;
        }
    }
}
