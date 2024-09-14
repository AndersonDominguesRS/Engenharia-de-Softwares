package com.example.disciplina.controller;
import com.example.disciplina.exception.ResourseNotFoundException;
import com.example.disciplina.model.Disciplina;
import com.example.disciplina.payload.MessagePayload;
import com.example.disciplina.service.DisciplinaService;
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
public class DisciplinaController {

    final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }


    @GetMapping@Operation(summary = " -- LISTAGEM DE DISCIPLINAS")
    public List<Disciplina> getAl(@RequestParam(required = false) String nome){
        return disciplinaService.lista();
    }

    @GetMapping("/{id}") @Operation (summary = " -- CONSULTA DISCIPLINA POR ID")
    public ResponseEntity<?> getDisciplina(@PathVariable Integer id){
        try {
            Optional<Disciplina> encontrado = disciplinaService.aluinoId(id);
            return ResponseEntity.ok(encontrado);

        }catch (ResourseNotFoundException ex){
            Map<String, String> message= Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "DISCIPLINA INSERIDA COM SUCESSO",
                    content = {@Content(mediaType = "appication/json",
                            schema = @Schema(implementation = Disciplina.class))}
            )
    })

    @PostMapping@Operation (summary = " -- INSERIR UMA NOVA DISCIPLINA")
    public ResponseEntity<MessagePayload> insert(@RequestBody Disciplina disciplina){
        log.info("Inserindo um disciplina {}", disciplina);
        disciplinaService.salva(disciplina);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }


    @PutMapping("/{id}")@Operation (summary = " -- ATUALIZANDO UMA DISCIPLINA")
    public ResponseEntity<MessagePayload> update(@PathVariable int id, @RequestBody Disciplina disciplina){
        try {
            disciplinaService.atualiza(id, disciplina);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")@Operation (summary = " -- DELETANDO UMA DISCIPLINA")
    public ResponseEntity<MessagePayload> delete(@PathVariable int id){
        try {
            disciplinaService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Excluido"));
        } catch (ResourseNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
