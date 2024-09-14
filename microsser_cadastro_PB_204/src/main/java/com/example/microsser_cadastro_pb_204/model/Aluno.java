package com.example.microsser_cadastro_pb_204.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "TB_ALUNO")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(name= "escola_id")
    private String escolaId;


}
