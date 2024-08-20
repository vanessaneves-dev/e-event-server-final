package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/organizer")
public class OrganizerControle {

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

    @GetMapping("/{id}")
    public ResponseEntity<OrganizerEntity> getById(@PathVariable UUID id) {
        Optional<OrganizerEntity> organizer = organizerService.getOrganizerById(id);
        return organizer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganizerEntity>> getAll() {
        List<OrganizerEntity> organizers = organizerService.getAllOrganizers();
        return ResponseEntity.ok().body(organizers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            organizerService.deleteOrganizer(id);
            return ResponseEntity.ok().body("Organizer deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
