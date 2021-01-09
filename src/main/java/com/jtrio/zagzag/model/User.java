package com.jtrio.zagzag.model;


import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.security.UserRole;
import com.jtrio.zagzag.user.UserDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data //getter setter 
@EntityListeners(value = {AuditingEntityListener.class})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//sql에서 자동생성되도록!
    private Long id;
    private String email;
    private String pass;
    private String name;
    private Gender gender;
    private String addr;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime created;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updated;

//    public UserDto toUserDto(){
//        UserDto userDto = new UserDto();
//        userDto.setEmail(email);
//        userDto.setName(name);
//        userDto.setGender(gender);
//        userDto.setAddr(addr);
//
//        return userDto;
//    }
}
