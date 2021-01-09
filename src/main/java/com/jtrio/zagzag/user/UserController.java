package com.jtrio.zagzag.user;

import com.jtrio.zagzag.exception.DuplicateEmailException;
import com.jtrio.zagzag.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
//    private final UserService userService;
    private final UserService userService;


    /**
     * catch(정확하게  예외 클래스 지정해주기 )
     **/

   @PostMapping
   public UserDto joinUser(@RequestBody UserCommand.CreateUser user){
       return userService.joinUser(user);
   }

    @GetMapping("/duplicate-email")
    public boolean getUserEmail(String email){
       return userService.findUserEmail(email);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserCommand.UpdateUser user) {
        return userService.updateUser(id, user);
    }

}



