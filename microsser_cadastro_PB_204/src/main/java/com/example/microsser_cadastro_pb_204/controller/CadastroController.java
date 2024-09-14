package com.example.microsser_cadastro_pb_204.controller;

import com.example.microsser_cadastro_pb_204.model.Aluno;
import com.example.microsser_cadastro_pb_204.model.Cadastro;
import com.example.microsser_cadastro_pb_204.service.CadastroService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }


    @Operation (summary = "LISTA DE CADASTROS")
    @GetMapping
    public List<Cadastro> getCadastro(){

        return cadastroService.listaCadastro();
    }

    @Operation (summary = "CADASTRO DE ALUNO EM UMA DISCIPLINA")
    @GetMapping("/{id_aluno}/{id_disciplina}")
    public Cadastro getAlunoEscola(@PathVariable Integer id_aluno, @PathVariable Integer id_disciplina ){
        return cadastroService.novaMatricula(id_aluno, id_disciplina );
    }

}
