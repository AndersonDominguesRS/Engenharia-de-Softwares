package com.example.disciplina.service;


import com.example.disciplina.filters.DisciplinaFilters;
import com.example.disciplina.model.Disciplina;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Optional;

public interface DisciplinaService {

    List<Disciplina> lista();
    Optional<Disciplina> aluinoId(Integer id);
    void salva(Disciplina disciplina);
    void delete(Integer id);
    void atualiza(Integer id, Disciplina disciplina) throws JsonProcessingException;

    List<Disciplina> filter(DisciplinaFilters disciplinaFilters);

}
