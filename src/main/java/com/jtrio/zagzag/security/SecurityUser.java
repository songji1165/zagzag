package com.jtrio.zagzag.security;

import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.user.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//도메인 클래스는 필드만 담고 있어야함.  -> repo, service등을 담고 있으면 안됨
@RequiredArgsConstructor
@Data
public class SecurityUser implements UserDetails {
    private final User userDetail;

    private User user;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
    }

    public String getPassword(){
        return user.getPass();
    }

    public String getUsername(){
        return user.getEmail();
    }

    public boolean isAccountNonExpired(){
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    }

    public boolean isCredentialsNonExpired(){
        return true;
    }

    public boolean isEnabled(){
        return true;
    }
}
