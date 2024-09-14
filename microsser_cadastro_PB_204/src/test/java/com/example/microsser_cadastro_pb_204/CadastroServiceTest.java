package com.example.microsser_cadastro_pb_204;

import com.example.microsser_cadastro_pb_204.model.Cadastro;
import com.example.microsser_cadastro_pb_204.service.CadastroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CadastroServiceTest {
    @Autowired
    CadastroService cadastroService;

    @Test
    public void test() {
        List<Cadastro> cadastros = cadastroService.listaCadastro();
        int total = cadastros.size();
        Cadastro cadastro=new Cadastro();

        cadastro.setNomeDisciplina("Teste");
        cadastro.setId_disciplina(10);
        cadastro.setNomeAluno("Aluno teste");
        cadastro.setId_aluno(10);

        cadastroService.salvaCadastro(cadastro);
        cadastros = cadastroService.listaCadastro();
        int totalNovo = cadastros.size();

        assertEquals(total +1, totalNovo);

    }
}
