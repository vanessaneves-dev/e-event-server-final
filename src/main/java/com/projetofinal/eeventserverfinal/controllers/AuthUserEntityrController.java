package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.dto.AuthOrganizerDTO;
import com.projetofinal.eeventserverfinal.dto.AuthUserEntityRequestDTO;
import com.projetofinal.eeventserverfinal.dto.AuthUserEntityResponseDTO;
import com.projetofinal.eeventserverfinal.service.AuthOrganizerService;
import com.projetofinal.eeventserverfinal.service.AuthUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthUserEntityrController {

    @Autowired
    private AuthUserEntityService authUserEntityService;



    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody AuthUserEntityRequestDTO authUserEntityRequestDTO) { try {

        var token = this.authUserEntityService.execute(authUserEntityRequestDTO);
        return ResponseEntity.ok().body(token);


    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    }
}
