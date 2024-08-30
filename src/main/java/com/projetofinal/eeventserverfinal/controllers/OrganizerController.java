package com.projetofinal.eeventserverfinal.controllers;


import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.service.OrganizerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/organizer")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @PostMapping("/new")
    public ResponseEntity<Object> create(@Valid @RequestBody OrganizerEntity organizerEntity) {
        try {
            var result = this.organizerService.execute(organizerEntity);
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); }

    }





    // Novo endpoint para listar todos os organizadores
    @GetMapping("/all")
    public ResponseEntity<List<OrganizerEntity>> getAllOrganizers() {
        try {
            var organizers = this.organizerService.getAllOrganizers();
            return ResponseEntity.ok().body(organizers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/")

    public ResponseEntity<Object> getById(HttpServletRequest request) {
        System.out.println("Endpoint / foi chamado");
        var organizerId = request.getAttribute("organizer_id");

        //verificação de erros
        // Verificar se o user_id foi recuperado com sucesso
        if (organizerId == null) {
            System.out.println("organizer_id não foi encontrado no atributo da requisição");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("organizer_id não encontrado");
        }

        try{
            // Verificar o user_id antes da conversão
            System.out.println("organizer_id recuperado: " + organizerId.toString());
            var organizer = this.organizerService
                    .executeProfile(UUID.fromString(organizerId.toString()));
            // Verificar se o serviço executou corretamente
            System.out.println("Usuário recuperado com sucesso: " + organizer);

            return ResponseEntity.ok().body(organizer);
        } catch (Exception e ){

            // Capturar exceções e mostrar a mensagem de erro
            System.out.println("Erro ao recuperar o usuário: " + e.getMessage());

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    // Novo endpoint para obter um organizador por ID
//    @GetMapping("/")
//    public ResponseEntity<Object> getOrganizerById(@PathVariable UUID id) {
//        try {
//            var organizer = this.organizerService.getOrganizerById(id);
//            return ResponseEntity.ok().body(organizer);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    // Novo endpoint para atualizar um organizador
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateOrganizer(@PathVariable UUID id, @RequestBody OrganizerEntity updatedOrganizer) {
        try {
            var organizer = this.organizerService.updateOrganizer(id, updatedOrganizer);
            return ResponseEntity.ok().body(organizer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Novo endpoint para deletar um organizador
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteOrganizer(@PathVariable UUID id) {
        try {
            this.organizerService.deleteOrganizer(id);
            return ResponseEntity.ok().body("Organizer deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
