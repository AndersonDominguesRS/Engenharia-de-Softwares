package com.example.PB_Escola_2024.service;

import com.example.PB_Escola_2024.filters.UsuarioFilters;
import com.example.PB_Escola_2024.model.Role;
import com.example.PB_Escola_2024.model.Usuario;

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
