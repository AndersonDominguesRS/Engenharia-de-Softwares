package com.example.pb_aluno_microsser.controller;


import com.example.pb_aluno_microsser.exception.ResourseNotFoundException;
import com.example.pb_aluno_microsser.model.Aluno;
import com.example.pb_aluno_microsser.payload.MessagePayload;
import com.example.pb_aluno_microsser.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@Slf4j
public class AlunoController {

    final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @GetMapping
    public List<Aluno> getAl(@RequestParam(required = false) String nome){
        return alunoService.lista();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAluno(@PathVariable Integer id){
        try {
            Optional<Aluno> encontrado = alunoService.aluinoId(id);
            return ResponseEntity.ok(encontrado);

        }catch (ResourseNotFoundException ex){
            Map<String, String> message= Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

    @Operation (summary = "Inserindo um aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atualizado inserido com sucesso",
                    content = {@Content(mediaType = "appication/json",
                            schema = @Schema(implementation = Aluno.class))}
            )
    })

    @PostMapping
    public ResponseEntity<MessagePayload> insert(@RequestBody Aluno aluno){
        log.info("Inserindo um aluno {}", aluno);
        alunoService.salva(aluno);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }

    @Operation (summary = "Atualizando um aluno")
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
    public ResponseEntity<MessagePayload> update(@PathVariable int id, @RequestBody Aluno aluno){
        try {
            alunoService.atualiza(id, aluno);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable int id){
        try {
            alunoService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Excluido"));
        } catch (ResourseNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
