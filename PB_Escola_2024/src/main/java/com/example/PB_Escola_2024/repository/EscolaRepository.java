package com.example.PB_Escola_2024.repository;

import com.example.PB_Escola_2024.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Integer> {
    List<Escola> findAllByNome(String nome);
}
