package com.projetofinal.eeventserverfinal.service;

import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.models.UserEntity;
import com.projetofinal.eeventserverfinal.models.UserEventEntity;
import com.projetofinal.eeventserverfinal.repository.EventRepository;
import com.projetofinal.eeventserverfinal.repository.UserEventRepository;
import com.projetofinal.eeventserverfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public UserEventEntity confirmPresence(UUID userId, UUID eventId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<EventEntity> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent()) {
            UserEventEntity userEvent = userEventRepository.findByUserAndEvent(user.get(), event.get())
                    .orElse(new UserEventEntity());

            userEvent.setUser(user.get());
            userEvent.setEvent(event.get());
            userEvent.setConfirmed(true);

            return userEventRepository.save(userEvent);
        }

        throw new RuntimeException("Usuário ou Evento não encontrado");
    }

    public UserEventEntity favoriteEvent(UUID userId, UUID eventId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<EventEntity> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent()) {
            UserEventEntity userEvent = userEventRepository.findByUserAndEvent(user.get(), event.get())
                    .orElse(new UserEventEntity());

            userEvent.setUser(user.get());
            userEvent.setEvent(event.get());
            userEvent.setFavorited(true);

            return userEventRepository.save(userEvent);
        }

        throw new RuntimeException("Usuário ou Evento não encontrado");
    }

    public void removeConfirmation(UUID userId, UUID eventId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<EventEntity> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent()) {
            Optional<UserEventEntity> userEvent = userEventRepository.findByUserAndEvent(user.get(), event.get());

            if (userEvent.isPresent()) {
                userEvent.get().setConfirmed(false);
                userEventRepository.save(userEvent.get());
            } else {
                throw new RuntimeException("Relação Usuário-Evento não encontrada");
            }
        } else {
            throw new RuntimeException("Usuário ou Evento não encontrado");
        }
    }

    public void removeFavorite(UUID userId, UUID eventId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<EventEntity> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent()) {
            Optional<UserEventEntity> userEvent = userEventRepository.findByUserAndEvent(user.get(), event.get());

            if (userEvent.isPresent()) {
                userEvent.get().setFavorited(false);
                userEventRepository.save(userEvent.get());
            } else {
                throw new RuntimeException("Relação Usuário-Evento não encontrada");
            }
        } else {
            throw new RuntimeException("Usuário ou Evento não encontrado");
        }
    }

    public List<UserEventEntity> getConfirmedEvents(UUID userId) {
        System.out.println("Fetching confirmed events for userId: " + userId); // Log para depuração
        // Lógica para obter eventos confirmados
        List<UserEventEntity> events = userEventRepository.findByUserIdAndIsConfirmedTrue(userId);
        System.out.println("Fetched events: " + events); // Log dos eventos obtidos
        return events;
    }

    public List<UserEventEntity> getFavoritedEvents(UUID userId) {
        System.out.println("Fetching favorited events for userId: " + userId); // Log para depuração
        // Lógica para obter eventos favoritados
        List<UserEventEntity> events = userEventRepository.findByUserIdAndIsFavoritedTrue(userId);
        System.out.println("Fetched events: " + events); // Log dos eventos obtidos
        return events;
    }


}
