package com.example.PB_Escola_2024.service;

import com.example.PB_Escola_2024.filters.EscolaFilters;
import com.example.PB_Escola_2024.filters.UsuarioFilters;
import com.example.PB_Escola_2024.model.Aluno;
import com.example.PB_Escola_2024.model.Escola;
import com.example.PB_Escola_2024.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface EscolaService {
    List<Escola> lista();
    Optional<Escola> listaId(Integer id);
    List<Escola> findAll(int page, int size, boolean ascending);
    List<Escola> listaNome(String name);
    void salva(Escola escola);
    void delete(Integer id);
    void atualiza(Integer id, Escola escola);

    List<Escola> filter(EscolaFilters escolaFilters);

}
