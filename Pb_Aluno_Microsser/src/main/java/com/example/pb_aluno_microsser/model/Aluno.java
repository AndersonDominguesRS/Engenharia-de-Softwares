package com.example.pb_aluno_microsser.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nome;
    @Column(name= "escola_id")
    private String escolaId;


}
