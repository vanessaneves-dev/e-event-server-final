package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.UserEntity;
import com.projetofinal.eeventserverfinal.repository.UserRepository;
import com.projetofinal.eeventserverfinal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<Object> create(@Valid @RequestBody UserEntity userEntity){
        try {
            var result = this.userService.execute(userEntity);
            return ResponseEntity.ok().body(result);


        } catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }




    }


}


