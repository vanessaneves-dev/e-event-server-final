package com.projetofinal.eeventserverfinal.service;


import com.projetofinal.eeventserverfinal.dto.CreateEventDTO;
import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventEntity execute(EventEntity eventEntity){
        return this.eventRepository.save(eventEntity);
    }

    // Método para atualizar um evento
    public EventEntity updateEvent(UUID id, CreateEventDTO updateEventDTO, UUID organizerId) {
        var event = this.eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new RuntimeException("Você não tem permissão para atualizar este evento");
        }

        event.setTitle(updateEventDTO.getTitle());
        event.setDescription(updateEventDTO.getDescription());
        event.setLocation(updateEventDTO.getLocation());
        event.setDate(updateEventDTO.getDate());
        event.setTime(updateEventDTO.getTime());
        event.setCategory(updateEventDTO.getCategory());
        event.setImage(updateEventDTO.getImage());

        return this.eventRepository.save(event);
    }

    // Método para deletar um evento
    public void deleteEvent(UUID id, UUID organizerId) {
        var event = this.eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new RuntimeException("Você não tem permissão para deletar este evento");
        }

        this.eventRepository.delete(event);
    }

    // Método para obter todos os eventos
    public List<EventEntity> getAllEvents() {
        return this.eventRepository.findAll();
    }

    // Método para obter um evento por ID
    public EventEntity getEventById(UUID id) {
        return this.eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }


}
