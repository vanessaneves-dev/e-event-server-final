package com.projetofinal.eeventserverfinal.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class OrganizerEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
        private String name;

        @Pattern(regexp = "\\S+", message = ("Não pode aver espaços neste campo"))
        private String username;

        @Email(message = "O e-mail precisa ser válido")
        private String email;

        @Length(min = 6, max = 200, message = "Senha deve ter entre 6 e 10 ")
        private String password;

        private String image;
}
