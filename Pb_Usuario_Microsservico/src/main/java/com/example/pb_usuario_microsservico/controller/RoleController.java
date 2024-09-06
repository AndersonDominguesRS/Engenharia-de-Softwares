package com.example.pb_usuario_microsservico.controller;


import com.example.pb_usuario_microsservico.exception.ResourseNotFoundException;
import com.example.pb_usuario_microsservico.model.Role;
import com.example.pb_usuario_microsservico.payload.MessagePayload;
import com.example.pb_usuario_microsservico.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public List<Role> getAl(@RequestParam(required = false) String nome){
        return roleService.lista();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable Integer id){
        try {
            Optional<Role> encontrado = roleService.listaId(id);
            return ResponseEntity.ok(encontrado);

        }catch (ResourseNotFoundException ex){
            Map<String, String> message= Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

    @Operation(summary = "Inserindo um role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atualizado inserido com sucesso",
                    content = {@Content(mediaType = "appication/json",
                            schema = @Schema(implementation = Role.class))}
            )
    })

    @PostMapping
    public ResponseEntity<MessagePayload> insert(@RequestBody Role role){
        roleService.salva(role);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }

    @Operation (summary = "Atualizando um role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Atualizado com sucesso",
                    content = {@Content(mediaType = "appication/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "ocorreu um erro",
                    content = {@Content(mediaType = "appication/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<MessagePayload> update(@PathVariable int id, @RequestBody Role role){
        try {
            roleService.atualiza(id, role);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable int id){
        try {
            roleService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Excluido"));
        } catch (ResourseNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
