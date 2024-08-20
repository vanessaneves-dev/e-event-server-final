package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.repository.EventRepository;
import com.projetofinal.eeventserverfinal.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping("/new")
    public EventEntity create(@Valid @RequestBody EventEntity eventEntity){
        return this.eventService.execute(eventEntity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventEntity> getById(@PathVariable UUID id) {
        Optional<EventEntity> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventEntity>> getAll() {
        List<EventEntity> events = eventService.getAllEvents();
        return ResponseEntity.ok().body(events);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventEntity> update(@PathVariable UUID id, @Valid @RequestBody EventEntity updatedEvent) {
        try {
            EventEntity event = eventService.updateEvent(id, updatedEvent);
            return ResponseEntity.ok().body(event);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok().body("Event deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
