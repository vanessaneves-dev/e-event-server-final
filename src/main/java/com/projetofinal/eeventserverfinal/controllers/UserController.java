package com.projetofinal.eeventserverfinal.controllers;



import com.projetofinal.eeventserverfinal.models.UserEntity;
import com.projetofinal.eeventserverfinal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

        @Autowired
        private UserService userService;


    @PostMapping("/auth")
    public ResponseEntity<Object> create(@Valid @RequestBody UserEntity userEntity){
        try {
            var result = this.userService.execute(userEntity);
            return ResponseEntity.ok().body(result);


        } catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }


    //get do proprio ususario feito com rocketseat
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getById(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        try{
        var user = this.userService
                .execute(UUID.fromString(userId.toString()));
        return ResponseEntity.ok().body(user);
    } catch (Exception e ){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

}


