package com.example.pb_usuario_microsservico.repository;

import com.example.pb_usuario_microsservico.model.Role;
import com.example.pb_usuario_microsservico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.email=?1 or u.nome=?2")
    List<Usuario> findAllBy(String email, String nome);

    @Query("select u from Usuario u inner join u.roles roles where roles in :roles")
    List<Usuario> findAllByRole(@Param("roles") List<Role> roles);

}
