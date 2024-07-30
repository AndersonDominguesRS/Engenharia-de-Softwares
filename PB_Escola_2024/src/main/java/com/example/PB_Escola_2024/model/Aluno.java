package com.example.PB_Escola_2024.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_ALUNO")
public class Aluno {

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name = "TB_NOME_ALUNO")
    private String nome;

    public Aluno (){
    }

    public Aluno(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }
}
