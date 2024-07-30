package com.example.PB_Escola_2024;

import com.example.PB_Escola_2024.model.Usuario;
import com.example.PB_Escola_2024.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsuarioTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    @DisplayName("Deve inserir um usuario")
    public void testInsert() {
        List<Usuario> usuarios = usuarioService.lista();
        int total = usuarios.size();
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Do Teste");

        usuarioService.salva(usuario);

        if (total < usuarioService.lista().size())
            assertEquals(total + 1, usuarioService.lista().size());

    }
}
