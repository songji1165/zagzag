package com.jtrio.zagzag.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
//    private final UserService userService;
    private final UserService userService;


    /**
     * 1. Post : 회원가입 RequestBody (email, pass, name, gender, addr) /join
     * 2. name 조회 : get /user/name
     * 3. email 조회 : get /user/email
     **/

   @PostMapping
   public ResponseEntity<UserDto> joinUser(@RequestBody UserCommand.CreateUser user){

       return new ResponseEntity<UserDto>(userService.joinUser(user), HttpStatus.OK);
   }

    @GetMapping("/duplicate-email")
    public boolean getUserEmail(String email){
        return userService.findUserEmail(email);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserCommand.UpdateUser user) {
        return new ResponseEntity<UserDto>(userService.updateUser(id, user), HttpStatus.OK);
    }

}
