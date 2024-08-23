package com.projetofinal.eeventserverfinal.controllers;



import com.projetofinal.eeventserverfinal.models.UserEntity;
import com.projetofinal.eeventserverfinal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

        @Autowired
        private UserService userService;


    @PostMapping("/new")
    public ResponseEntity<Object> create(@Valid @RequestBody UserEntity userEntity){
        try {
            var result = this.userService.execute(userEntity);
            return ResponseEntity.ok().body(result);


        } catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllUsers() {
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }


    //get do perfil ususario logado
    @GetMapping("/authok")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getById(HttpServletRequest request) {
        System.out.println("Endpoint /authok foi chamado");
        var userId = request.getAttribute("user_id");

        //verificação de erros
        // Verificar se o user_id foi recuperado com sucesso
        if (userId == null) {
            System.out.println("user_id não foi encontrado no atributo da requisição");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user_id não encontrado");
        }

        try{
            // Verificar o user_id antes da conversão
            System.out.println("user_id recuperado: " + userId.toString());
        var user = this.userService
                .executeProfile(UUID.fromString(userId.toString()));
            // Verificar se o serviço executou corretamente
            System.out.println("Usuário recuperado com sucesso: " + user);

        return ResponseEntity.ok().body(user);
    } catch (Exception e ){

            // Capturar exceções e mostrar a mensagem de erro
            System.out.println("Erro ao recuperar o usuário: " + e.getMessage());

        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

}


