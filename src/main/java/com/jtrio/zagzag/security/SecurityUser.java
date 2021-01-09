package com.jtrio.zagzag.security;

import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.user.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//도메인 클래스는 필드만 담고 있어야함.  -> repo, service등을 담고 있으면 안됨
@RequiredArgsConstructor
@Data
public class SecurityUser implements UserDetails {
    private static final String ROLE_PREFIX = "ROLE_"; //security 권한자의 필수 명

    private final User userDetail;

    private User user;

    //접근 권한 부여
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));
        return authorities;
    }

    public String getPassword(){ return user.getPass(); }

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

    public boolean isEnabled(){ return true; }
}
