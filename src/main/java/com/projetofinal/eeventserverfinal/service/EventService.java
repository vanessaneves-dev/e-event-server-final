package com.projetofinal.eeventserverfinal.service;


import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventEntity execute(EventEntity eventEntity){
        return this.eventRepository.save(eventEntity);
    }

    public Optional<EventEntity> getEventById(UUID id) {
        return this.eventRepository.findById(id);
    }

    public List<EventEntity> getAllEvents() {
        return this.eventRepository.findAll();
    }

    public EventEntity updateEvent(UUID id, EventEntity updatedEvent) {
        return this.eventRepository.findById(id).map(event -> {
            event.setTitle(updatedEvent.getTitle());
            event.setDescription(updatedEvent.getDescription());
            event.setLocation(updatedEvent.getLocation());
            event.setDate(updatedEvent.getDate());
            event.setTime(updatedEvent.getTime());
            event.setCategory(updatedEvent.getCategory());
            return this.eventRepository.save(event);
        }).orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }

    public void deleteEvent(UUID id) {
        if (this.eventRepository.existsById(id)) {
            this.eventRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Event not found");
        }
    }
}
