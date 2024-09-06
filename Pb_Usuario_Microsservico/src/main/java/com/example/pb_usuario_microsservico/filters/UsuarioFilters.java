package com.example.pb_usuario_microsservico.filters;

import com.example.pb_usuario_microsservico.model.Role;
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
