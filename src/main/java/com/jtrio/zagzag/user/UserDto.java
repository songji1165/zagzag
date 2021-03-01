package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String name;
    private Gender gender;
    private String addr;

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setGender(user.getGender());
        userDto.setAddr(user.getAddr());

        return userDto;
    }
}
