package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import lombok.Data;

public class UserCommand {

    @Data
    public static class CreateUser {
        private String email;
        private String pass;
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

}
