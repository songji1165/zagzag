package com.jtrio.zagzag.user;

import com.jtrio.zagzag.exception.NotFoundUserException;
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
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody UserCommand.UpdateUser user) {
//        try{
           return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
//        }catch (RuntimeException e) {
//            NotFoundUserException notFound = new NotFoundUserException("해당 사용자를 찾을 수 없습니다.");
//           return new ResponseEntity<>(notFound,HttpStatus.NOT_FOUND);
//        }
    }

}
