package com.example.microsser_cadastro_pb_204.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_aluno;
    private String nomeAluno;
    private Integer id_disciplina;
    private String nomeDisciplina;


}
