package com.projetofinal.eeventserverfinal.models;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(length = 500)
    private String description;

    @Embedded
    private Address location;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private String date;

    @JsonFormat(pattern = "HH:mm")
    private String time;


    private String category;
    private String image;
    private String maps;



    @ManyToOne
    @JoinColumn(name = "organizer_id", insertable = false, updatable = false)
    private OrganizerEntity organizer;

    @Column(name = "organizer_id")
    private UUID organizerId ;


}
