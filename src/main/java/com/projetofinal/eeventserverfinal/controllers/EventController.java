package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.dto.CreateEventDTO;
import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.repository.EventRepository;
import com.projetofinal.eeventserverfinal.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/organizer/event")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping("/new")
    @PreAuthorize("hasRole('ORGANIZER')")
    public EventEntity create(@Valid @RequestBody CreateEventDTO createEventDTO, HttpServletRequest request){
         var organizerId = request.getAttribute("organizer_id");
       // eventEntity.setOrganizerId(UUID.fromString(organizerId.toString()));
    var eventEntity =  EventEntity.builder()
                .organizerId(UUID.fromString(organizerId.toString()))
                .title(createEventDTO.getTitle())
                .description(createEventDTO.getDescription())
                .street(createEventDTO.getStreet())
                .number(createEventDTO.getNumber())
                .city(createEventDTO.getCity())
                .state(createEventDTO.getState())
                .postalCode(createEventDTO.getPostalCode())
                .date(createEventDTO.getDate())
                .time(createEventDTO.getTime())
                .category(createEventDTO.getCategory())
                .image(createEventDTO.getImage())
                .build();

        return this.eventService.execute(eventEntity);
    }


    // Endpoint para atualizar um evento
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Object> update(@PathVariable UUID id, @Valid @RequestBody CreateEventDTO updateEventDTO, HttpServletRequest request) {
        var organizerId = UUID.fromString(request.getAttribute("organizer_id").toString());
        try {
            var updatedEvent = this.eventService.updateEvent(id, updateEventDTO, organizerId);
            return ResponseEntity.ok().body(updatedEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para deletar um evento
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Object> delete(@PathVariable UUID id, HttpServletRequest request) {
        var organizerId = UUID.fromString(request.getAttribute("organizer_id").toString());
        try {
            this.eventService.deleteEvent(id, organizerId);
            return ResponseEntity.ok().body("Evento deletado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para obter todos os eventos
    @GetMapping("/all")
    public ResponseEntity<List<EventEntity>> getAllEvents() {
        var events = this.eventService.getAllEvents();
        return ResponseEntity.ok().body(events);
    }

    // Endpoint para obter um evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable UUID id) {
        try {
            var event = this.eventService.getEventById(id);
            return ResponseEntity.ok().body(event);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/organizer/{organizerId}")
    public ResponseEntity<Object> getEventsByOrganizerId(@PathVariable UUID organizerId) {
        try {
            var events = this.eventService.getEventsByOrganizerId(organizerId);
            return ResponseEntity.ok().body(events);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
