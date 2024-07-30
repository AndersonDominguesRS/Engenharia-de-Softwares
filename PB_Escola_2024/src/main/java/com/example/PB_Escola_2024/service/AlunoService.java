package com.example.PB_Escola_2024.service;

import com.example.PB_Escola_2024.filters.AlunoFilters;
import com.example.PB_Escola_2024.filters.UsuarioFilters;
import com.example.PB_Escola_2024.model.Aluno;
import com.example.PB_Escola_2024.model.Usuario;

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
