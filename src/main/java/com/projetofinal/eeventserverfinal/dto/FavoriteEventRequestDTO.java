package com.projetofinal.eeventserverfinal.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class FavoriteEventRequestDTO {

    private UUID userId;
    private UUID eventId;
}
