package com.example.pb_aluno_microsser;

import com.example.pb_aluno_microsser.model.Aluno;
import com.example.pb_aluno_microsser.service.AlunoService;
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
