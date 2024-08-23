package com.projetofinal.eeventserverfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserResponseDTO {

        private UUID id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String image;
}
