package com.example.PB_Escola_2024.controller;

import com.example.PB_Escola_2024.exception.ResourseNotFoundException;
import com.example.PB_Escola_2024.model.Usuario;
import com.example.PB_Escola_2024.payload.MessagePayload;
import com.example.PB_Escola_2024.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public List<Usuario> getAl(@RequestParam(required = false) String nome){
        return usuarioService.lista();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Integer id){
        try {
            Optional<Usuario> encontrado = usuarioService.listaId(id);
            return ResponseEntity.ok(encontrado);

        }catch (ResourseNotFoundException ex){
            Map<String, String> message= Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

    @Operation(summary = "Inserindo um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atualizado inserido com sucesso",
                    content = {@Content(mediaType = "appication/json",
                            schema = @Schema(implementation = Usuario.class))}
            )
    })

    @PostMapping
    public ResponseEntity<MessagePayload> insert(@RequestBody Usuario usuario){
        usuarioService.salva(usuario);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }

    @Operation (summary = "Atualizando um usuario")
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
    public ResponseEntity<MessagePayload> update(@PathVariable int id, @RequestBody Usuario usuario){
        try {
            usuarioService.atualiza(id, usuario);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable int id){
        try {
            usuarioService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Excluido"));
        } catch (ResourseNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
