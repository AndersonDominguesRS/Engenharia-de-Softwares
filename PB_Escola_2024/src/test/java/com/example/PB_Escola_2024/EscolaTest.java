package com.example.PB_Escola_2024;

import com.example.PB_Escola_2024.model.Escola;
import com.example.PB_Escola_2024.service.EscolaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EscolaTest {
    @Autowired
    EscolaService escolaService;

    @Test
    @DisplayName("Deve retornar uma escola pelo nome")
    public void buscaNome() {
        List<Escola> result=escolaService.listaNome("ESCOLA TESTE 1");
        assertEquals(1, result.size());

    }
}
