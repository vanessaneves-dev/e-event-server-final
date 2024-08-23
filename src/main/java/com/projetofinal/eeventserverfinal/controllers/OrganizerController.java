package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/organizer")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @PostMapping("/new")
    public ResponseEntity<Object> create(@Valid @RequestBody OrganizerEntity organizerEntity) {
        try {
            var result = this.organizerService.execute(organizerEntity);
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); }

    }
}
