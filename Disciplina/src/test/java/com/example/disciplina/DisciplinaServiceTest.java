package com.example.disciplina;

import com.example.disciplina.model.Disciplina;
import com.example.disciplina.service.DisciplinaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DisciplinaServiceTest {
    @Autowired
    DisciplinaService disciplinaService;

    @Test
    public void test() {
        List<Disciplina> disciplinas = disciplinaService.lista();
        int total = disciplinas.size();
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Disciplina");

        disciplinaService.salva(disciplina);
        disciplinas = disciplinaService.lista();
        int totalNovo = disciplinas.size();

        assertEquals(total +1, totalNovo);

    }
}
