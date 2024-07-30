package com.example.PB_Escola_2024.impl;

import com.example.PB_Escola_2024.exception.ResourseNotFoundException;
import com.example.PB_Escola_2024.filters.RoleFilters;
import com.example.PB_Escola_2024.model.Role;
import com.example.PB_Escola_2024.repository.RoleRepository;
import com.example.PB_Escola_2024.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final EntityManager entityManager;

    @Override
    public List<Role> lista() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> listaId(Integer id) {
        if (id < 0) {
            throw new ResourseNotFoundException("ID inexistente");
        } else {
            Optional<Role> roleOpt = roleRepository.findAll().stream().filter(a -> a.getId() == id).findFirst();

            if (roleOpt.isEmpty()) throw new ResourseNotFoundException("Role nao encontrado");

            return roleRepository.findById(id);
        }
    }

    @Override
    public void salva(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Integer id) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente no delete");
        }
        roleRepository.deleteById(id);
    }

    private boolean resourceNotFound(Integer id) {
        return roleRepository.findAll().stream().filter(a -> a.getId() == id).findFirst().isEmpty();
    }

    @Override
    public void atualiza(Integer id, Role role) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente na atualização");
        }

        role.setId(id);
        roleRepository.save(role);
    }

    @Override
    public List<Role> filter(RoleFilters roleFilter) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> role = cq.from(Role.class);
        List<Predicate> predicates = new ArrayList<>();

        if (roleFilter.getTipo().isPresent()) {
            String query= roleFilter.getTipo() + "%";
            Predicate tipo = cb.like(role.get("nome"), query);
            predicates.add(tipo);
        }

        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(predicates.toArray(Predicate[]::new));

        List<Role> resultList = entityManager.createQuery(cq).getResultList();


        return resultList;
    }
}
