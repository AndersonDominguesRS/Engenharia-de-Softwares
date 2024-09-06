package com.example.pb_escola_mircosservico.controller;


import com.example.pb_escola_mircosservico.exception.ResourseNotFoundException;
import com.example.pb_escola_mircosservico.model.Escola;
import com.example.pb_escola_mircosservico.payload.MessagePayload;
import com.example.pb_escola_mircosservico.service.EscolaService;
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
public class EscolaController {


    final EscolaService escolaService;

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }


    @GetMapping
    public List<Escola> getAl(@RequestParam(required = false) String nome){
        return escolaService.lista();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEscola(@PathVariable Integer id){
        try {
            Optional<Escola> encontrado = escolaService.listaId(id);
            return ResponseEntity.ok(encontrado);

        }catch (ResourseNotFoundException ex){
            Map<String, String> message= Map.of("Message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

    }

    @Operation(summary = "Inserindo um escola")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atualizado inserido com sucesso",
                    content = {@Content(mediaType = "appication/json",
                            schema = @Schema(implementation = Escola.class))}
            )
    })

    @PostMapping
    public ResponseEntity<MessagePayload> insert(@RequestBody Escola escola){
        escolaService.salva(escola);
        return ResponseEntity.ok(new MessagePayload("Criado com sucesso"));
    }

    @Operation (summary = "Atualizando um escola")
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
    public ResponseEntity<MessagePayload> update(@PathVariable int id, @RequestBody Escola escola){

        try {

            log.info("Inserindo um aluno {}", escola.getAlunos());

            escolaService.atualiza(id, escola);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessagePayload> delete(@PathVariable int id){
        try {
            escolaService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayload("Excluido"));
        } catch (ResourseNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload(ex.getMessage()));
        }
    }
}
