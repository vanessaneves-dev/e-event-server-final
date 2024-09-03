package com.projetofinal.eeventserverfinal.controllers;

import com.projetofinal.eeventserverfinal.dto.ConfirmPresenceRequestDTO;
import com.projetofinal.eeventserverfinal.dto.FavoriteEventRequestDTO;
import com.projetofinal.eeventserverfinal.models.UserEventEntity;
import com.projetofinal.eeventserverfinal.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/user-event")
public class UserEventController {

    @Autowired
    private UserEventService userEventService;

    @PostMapping("/confirm")
    public ResponseEntity<UserEventEntity> confirmPresence(@RequestBody ConfirmPresenceRequestDTO request) {
        System.out.println("Confirm Presence Request - UserId: " + request.getUserId() + ", EventId: " + request.getEventId());
        UserEventEntity userEvent = userEventService.confirmPresence(request.getUserId(), request.getEventId());
        return ResponseEntity.ok(userEvent);
    }

    @DeleteMapping("/confirm/{userId}/{eventId}")
    public ResponseEntity<Void> removeConfirmation(@PathVariable UUID userId, @PathVariable UUID eventId) {
        userEventService.removeConfirmation(userId, eventId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/favorite")
    public ResponseEntity<UserEventEntity> favoriteEvent(@RequestBody FavoriteEventRequestDTO request) {
        System.out.println("Favorite Event Request - UserId: " + request.getUserId() + ", EventId: " + request.getEventId());
        UserEventEntity userEvent = userEventService.favoriteEvent(request.getUserId(), request.getEventId());
        return ResponseEntity.ok(userEvent);
    }

    @DeleteMapping("/favorite")
    public ResponseEntity<Void> removeFavorite(@RequestBody FavoriteEventRequestDTO request) {
        userEventService.removeFavorite(request.getUserId(), request.getEventId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/confirmed/{userId}")
    public ResponseEntity<List<UserEventEntity>> getConfirmedEvents(@PathVariable UUID userId) {
        System.out.println("Received userId: " + userId); // Log do userId recebido
        List<UserEventEntity> confirmedEvents = userEventService.getConfirmedEvents(userId);
        System.out.println("Number of confirmed events: " + confirmedEvents.size()); // Log do número de eventos retornados
        return ResponseEntity.ok(confirmedEvents);
    }

    @GetMapping("/favorites/{userId}")
    public ResponseEntity<List<UserEventEntity>> getFavoritedEvents(@PathVariable UUID userId) {
        System.out.println("Received userId: " + userId); // Log do userId recebido
        List<UserEventEntity> favoritedEvents = userEventService.getFavoritedEvents(userId);
        System.out.println("Number of favorited events: " + favoritedEvents.size()); // Log do número de eventos retornados
        return ResponseEntity.ok(favoritedEvents);
    }


}
