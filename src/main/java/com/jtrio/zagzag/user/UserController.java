package com.jtrio.zagzag.user;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * catch(정확하게  예외 클래스 지정해주기 )
     **/

   @PostMapping
   public UserDto joinUser(@RequestBody UserCommand.CreateUser user){
       return userService.joinUser(user);
   }

   @GetMapping("/me")
   public String readUser(@AuthenticationPrincipal SecurityUser securityUser){
       return securityUser.getUsername();
   }

    @GetMapping("/duplicate-email")
    public boolean getUserEmail(String email){
       return userService.findUserEmail(email);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@AuthenticationPrincipal SecurityUser securityUser, @RequestBody UserCommand.UpdateUser user) {
        return userService.updateUser(securityUser, user);
    }

}



