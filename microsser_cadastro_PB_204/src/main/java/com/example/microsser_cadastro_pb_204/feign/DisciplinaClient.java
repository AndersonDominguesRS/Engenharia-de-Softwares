package com.example.microsser_cadastro_pb_204.feign;

import com.example.microsser_cadastro_pb_204.model.Disciplina;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("Microsservico-Disciplina")
public interface DisciplinaClient {

    @GetMapping("/{id}")
    Disciplina ListDisciplinaById (@PathVariable Integer id);

    @GetMapping()
    List<Disciplina> ListDisciplina ();

}
