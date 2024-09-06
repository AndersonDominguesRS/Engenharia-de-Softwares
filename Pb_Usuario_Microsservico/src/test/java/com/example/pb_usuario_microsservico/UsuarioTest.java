package com.example.pb_usuario_microsservico;

import com.example.pb_usuario_microsservico.model.Usuario;
import com.example.pb_usuario_microsservico.service.UsuarioService;
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
