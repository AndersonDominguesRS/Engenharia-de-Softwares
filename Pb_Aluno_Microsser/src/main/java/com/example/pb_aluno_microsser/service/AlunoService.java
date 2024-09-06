package com.example.pb_aluno_microsser.service;

import com.example.pb_aluno_microsser.filters.AlunoFilters;
import com.example.pb_aluno_microsser.model.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoService {

    List<Aluno> lista();
    Optional<Aluno> aluinoId(Integer id);
    void salva(Aluno aluno);
    void delete(Integer id);
    void atualiza(Integer id, Aluno aluno);

    List<Aluno> filter(AlunoFilters alunoFilters);

}
