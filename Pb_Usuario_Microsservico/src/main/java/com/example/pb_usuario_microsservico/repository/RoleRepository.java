package com.example.pb_usuario_microsservico.repository;

import com.example.pb_usuario_microsservico.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
