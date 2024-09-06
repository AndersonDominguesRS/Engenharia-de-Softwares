package com.example.pb_escola_mircosservico.service;
import com.example.pb_escola_mircosservico.filters.EscolaFilters;
import com.example.pb_escola_mircosservico.model.Aluno;
import com.example.pb_escola_mircosservico.model.Escola;

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
