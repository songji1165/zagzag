package com.jtrio.zagzag.user;

import com.jtrio.zagzag.enums.Gender;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String name;
    private Gender gender;
    private String addr;
}
