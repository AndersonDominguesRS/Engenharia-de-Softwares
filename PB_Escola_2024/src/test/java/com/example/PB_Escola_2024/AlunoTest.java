package com.example.PB_Escola_2024;

import com.example.PB_Escola_2024.model.Aluno;
import com.example.PB_Escola_2024.service.AlunoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AlunoTest {
    @Autowired
    AlunoService alunoService;

    @Test
    @DisplayName("Deve inserir um aluno")
    public void testInsert() {
        List<Aluno> alunos = alunoService.lista();
        int total = alunos.size();
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno Do Teste");

        alunoService.salva(aluno);

        if (total < alunoService.lista().size())
            assertEquals(total + 1, alunoService.lista().size());

    }
}
