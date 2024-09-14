package com.example.microsser_cadastro_pb_204.feign;

import com.example.microsser_cadastro_pb_204.model.Aluno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("Microsservico-Aluno")
public interface AlunoClient {

    @GetMapping("/{id}")
    Aluno ListAlunoById (@PathVariable Integer id);

    @GetMapping()
    List<Aluno> ListAluno ();

}
