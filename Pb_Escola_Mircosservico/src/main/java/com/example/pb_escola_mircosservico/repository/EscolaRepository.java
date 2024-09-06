package com.example.pb_escola_mircosservico.repository;

import com.example.pb_escola_mircosservico.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EscolaRepository extends JpaRepository<Escola, Integer> {
    List<Escola> findAllByNome(String nome);
}
