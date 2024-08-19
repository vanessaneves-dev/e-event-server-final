package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.dto.AuthOrganizerDTO;
import com.projetofinal.eeventserverfinal.service.AuthOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthOrganizerController {

    @Autowired
    private AuthOrganizerService authOrganizerService;

    @PostMapping("/organizer")
    public ResponseEntity<Object> create(@RequestBody AuthOrganizerDTO authOrganizerDTO) { try {
        String result = this.authOrganizerService.execute(authOrganizerDTO);
        return ResponseEntity.ok().body(result);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
    }
}
