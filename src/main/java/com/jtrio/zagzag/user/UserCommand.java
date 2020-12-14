package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.ErrorMsg;
import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//import static com.jtrio.zagzag.enums.ErrorMsg;
//import static com.jtrio.zagzag.enums.ErrorMsg.EMAIL;

public class UserCommand {
//    private ErrorMsg errMsg;

    @Data
    public static class CreateUser {
//        System.out.println(ErrorMsg.valueOf("EMAIL"));
//        @Email(message = ErrorMsg.EMAIL.toString())
        @Email(message = "이메일 형식을 확인해주세요.")
        private String email;
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String pass;
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;
        private Gender gender;
        private String addr;


        public User toUser(){
            User user = new User();
            user.setEmail(email);
            user.setPass(pass);
            user.setName(name);
            user.setGender(gender);
            user.setAddr(addr);
            return user;
        }
    }

    @Data
    public static class UpdateUser {
        private String name;
        private String addr;

        public User toUser(User user){
            user.setName(name);
            user.setAddr(addr);
            return user;
        }
    }

    @Data
    public static class CheckUser {
        private String name;
        private String email;
    }

}
