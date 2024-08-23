package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.dto.CreateEventDTO;
import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.repository.EventRepository;
import com.projetofinal.eeventserverfinal.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping("/new")
    public EventEntity create(@Valid @RequestBody CreateEventDTO createEventDTO, HttpServletRequest request){
         var organizerId = request.getAttribute("organizer_id");
       // eventEntity.setOrganizerId(UUID.fromString(organizerId.toString()));
    var eventEntity =  EventEntity.builder()
                .organizerId(UUID.fromString(organizerId.toString()))
                .title(createEventDTO.getTitle())
                .description(createEventDTO.getDescription())
                .location(createEventDTO.getLocation())
                .date(createEventDTO.getDate())
                .time(createEventDTO.getTime())
                .category(createEventDTO.getCategory())
                .image(createEventDTO.getImage())
                .build();

        return this.eventService.execute(eventEntity);
    }

}
