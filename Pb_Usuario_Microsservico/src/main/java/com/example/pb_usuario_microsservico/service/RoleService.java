package com.example.pb_usuario_microsservico.service;

import com.example.pb_usuario_microsservico.filters.RoleFilters;
import com.example.pb_usuario_microsservico.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> lista();
    Optional<Role> listaId(Integer id);
    void salva(Role role);
    void delete(Integer id);
    void atualiza(Integer id, Role role);

    List<Role> filter(RoleFilters roleFilter);

}
