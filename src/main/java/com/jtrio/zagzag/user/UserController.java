package com.jtrio.zagzag.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(name = "users")
public class UserController {
    public UserService userService;

    /**
     * 1. Post : 회원가입 RequestBody (email, pass, name, gender, addr) /join
     * 2. name 조회 : get /user/name
     * 3. email 조회 : get /user/email
     **/

   @PostMapping
   public UserDto joinUser(@RequestBody UserCommand.CreateUser user){
       return userService.joinUser(user);
   }

    @GetMapping("/duplicate-email")
    public boolean getUserEmail(String email){
        return userService.findUserEmail(email);
    }

    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserCommand.UpdateUser user) {
        return userService.updateUser(id, user);
    }

}
