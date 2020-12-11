package com.jtrio.zagzag.user;

import com.jtrio.zagzag.exception.DuplicateEmailException;
import com.jtrio.zagzag.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
   public ResponseEntity<Object> joinUser(@RequestBody UserCommand.CreateUser user){
        try{
            return new ResponseEntity<>(userService.joinUser(user), HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
   }

    @GetMapping("/duplicate-email")
    public ResponseEntity<Boolean> getUserEmail(String email){
       return new ResponseEntity<>(userService.findUserEmail(email), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody UserCommand.UpdateUser user) {
        try{ return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
        }catch (RuntimeException e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
