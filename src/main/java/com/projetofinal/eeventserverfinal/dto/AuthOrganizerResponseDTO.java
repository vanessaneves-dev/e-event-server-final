package com.projetofinal.eeventserverfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthOrganizerResponseDTO {

    private String access_organizer_token;
    private Long expires_in;
}