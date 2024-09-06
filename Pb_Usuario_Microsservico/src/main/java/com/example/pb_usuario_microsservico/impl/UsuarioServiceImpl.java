package com.example.pb_usuario_microsservico.impl;

import com.example.pb_usuario_microsservico.exception.ResourseNotFoundException;
import com.example.pb_usuario_microsservico.filters.UsuarioFilters;
import com.example.pb_usuario_microsservico.model.Role;
import com.example.pb_usuario_microsservico.model.Usuario;
import com.example.pb_usuario_microsservico.repository.UsuarioRepository;
import com.example.pb_usuario_microsservico.service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EntityManager entityManager;


    @Override
    public List<Usuario> lista() {

        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findByRole(List<Role> roles) {
        return usuarioRepository.findAllByRole(roles);
    }

    @Override
    public Optional<Usuario> listaId(Integer id) {
        if (id < 0) {
            throw new ResourseNotFoundException("ID inexistente");
        } else {
            Optional<Usuario> usuarioOpt = usuarioRepository.findAll().stream().filter(a -> a.getId() == id).findFirst();

            if (usuarioOpt.isEmpty()) throw new ResourseNotFoundException("Usuario nao encontrado");

            return usuarioRepository.findById(id);
        }
    }

    @Override
    public void salva(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente no delete");
        }
        usuarioRepository.deleteById(id);
    }

    private boolean resourceNotFound(Integer id) {
        return usuarioRepository.findAll().stream().filter(a -> a.getId() == id).findFirst().isEmpty();
    }

    @Override
    public void atualiza(Integer id, Usuario usuario) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente na atualização");
        }

        usuario.setId(id);
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> filter(UsuarioFilters usuarioFilters) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        List<Predicate> predicates = new ArrayList<>();

        if (usuarioFilters.getNome().isPresent()) {
            String query= usuarioFilters.getNome() + "%";
            Predicate nome = cb.like(usuario.get("nome"), query);
            predicates.add(nome);
        }

        if (usuarioFilters.getRoleUsuario().isPresent()) {
            List<Role> roleUsuario = usuarioFilters.getRoleUsuario().get();
            List<Integer> ids = roleUsuario.stream().map(Role::getId).toList();
            Predicate rolePredicate = usuario.join("roleUsuario").get("id").in(ids);
            predicates.add(rolePredicate);
        }
        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(predicates.toArray(Predicate[]::new));

        List<Usuario> resultList = entityManager.createQuery(cq).getResultList();


        return resultList;
    }
}