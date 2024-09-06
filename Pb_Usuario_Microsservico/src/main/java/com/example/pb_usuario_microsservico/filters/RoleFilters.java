package com.example.pb_usuario_microsservico.filters;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleFilters {

    private Optional<String> tipo;
}
