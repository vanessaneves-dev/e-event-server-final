package com.projetofinal.eeventserverfinal.service;


import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventEntity execute(EventEntity eventEntity){
        return this.eventRepository.save(eventEntity);
    }
}
