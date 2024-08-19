package com.projetofinal.eeventserverfinal.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity(name ="event_entity")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;

    @Embedded
    private Address location;

    private String date;
    private String time;
    private String category;

    @ManyToOne
    @JoinColumn(name = "organizer_id", insertable = false, updatable = false)
    private OrganizerEntity organizer;

    @Column(name = "organizer_id")
    private UUID organizerId ;

    @CreatedDate
    private LocalDateTime createAt;


}
