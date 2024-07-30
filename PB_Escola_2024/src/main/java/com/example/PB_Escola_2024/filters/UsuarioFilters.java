package com.example.PB_Escola_2024.filters;

import com.example.PB_Escola_2024.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioFilters {

    private Optional<String> nome;
    private Optional<List<Role>> roleUsuario;
}
