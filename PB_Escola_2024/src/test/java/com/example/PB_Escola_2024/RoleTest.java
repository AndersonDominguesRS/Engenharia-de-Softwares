package com.example.PB_Escola_2024;

import com.example.PB_Escola_2024.model.Role;
import com.example.PB_Escola_2024.service.RoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoleTest {

    @Autowired
    RoleService roleService;

    @Test
    @DisplayName("Deve inserir um role")
    public void testInsert() {
        List<Role> roles = roleService.lista();
        int total = roles.size();
        Role role = new Role();
        role.setTipo("Role Do Teste");

        roleService.salva(role);

        if (total < roleService.lista().size())
            assertEquals(total + 1, roleService.lista().size());

    }
}
