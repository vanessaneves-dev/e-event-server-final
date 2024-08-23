package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.dto.AuthUserRequestDTO;
import com.projetofinal.eeventserverfinal.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;



    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody AuthUserRequestDTO authUserRequestDTO) { try {

        var token = this.authUserService.execute(authUserRequestDTO);
        return ResponseEntity.ok().body(token);


    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    }
}
