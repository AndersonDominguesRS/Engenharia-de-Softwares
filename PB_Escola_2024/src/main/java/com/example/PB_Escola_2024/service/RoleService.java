package com.example.PB_Escola_2024.service;

import com.example.PB_Escola_2024.filters.RoleFilters;
import com.example.PB_Escola_2024.model.Role;

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
