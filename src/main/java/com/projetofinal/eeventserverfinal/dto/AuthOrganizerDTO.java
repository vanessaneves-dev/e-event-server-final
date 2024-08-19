package com.projetofinal.eeventserverfinal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthOrganizerDTO {
    private String email;
    private String password;
}
