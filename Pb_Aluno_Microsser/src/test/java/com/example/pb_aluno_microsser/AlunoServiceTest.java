package com.example.pb_aluno_microsser;

import com.example.pb_aluno_microsser.model.Aluno;
import com.example.pb_aluno_microsser.service.AlunoService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AlunoServiceTest {
    @Autowired
    AlunoService alunoService;

    @Test
    public void test() {
        List<Aluno> alunos = alunoService.lista();
        int total = alunos.size();
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno");

        alunoService.salva(aluno);
        alunos = alunoService.lista();
        int totalNovo = alunos.size();

        assertEquals(total +1, totalNovo);

    }
}
