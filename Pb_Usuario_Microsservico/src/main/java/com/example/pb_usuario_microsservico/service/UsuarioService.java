package com.example.pb_usuario_microsservico.service;

import com.example.pb_usuario_microsservico.filters.UsuarioFilters;
import com.example.pb_usuario_microsservico.model.Role;
import com.example.pb_usuario_microsservico.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> lista();
    List <Usuario> findByRole(List<Role> roles);
    Optional<Usuario> listaId(Integer id);
    void salva(Usuario usuario);
    void delete(Integer id);
    void atualiza(Integer id, Usuario usuario);

    List<Usuario> filter(UsuarioFilters usuarioFilters);
}
