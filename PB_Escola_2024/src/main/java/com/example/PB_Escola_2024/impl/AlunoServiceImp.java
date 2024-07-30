package com.example.PB_Escola_2024.impl;

import com.example.PB_Escola_2024.exception.ResourseNotFoundException;
import com.example.PB_Escola_2024.filters.AlunoFilters;
import com.example.PB_Escola_2024.model.Aluno;
import com.example.PB_Escola_2024.repository.AlunoRepository;
import com.example.PB_Escola_2024.service.AlunoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlunoServiceImp implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final EntityManager entityManager;

    @Override
    public List<Aluno> lista() {
        return alunoRepository.findAll();
    }

    @Override
    public Optional<Aluno> aluinoId(Integer id) {
        if (id < 0) {
            throw new ResourseNotFoundException("ID inexistente");
        } else {
            Optional<Aluno> alunoOpt = alunoRepository.findAll().stream().filter(a -> a.getId() == id).findFirst();

            if (alunoOpt.isEmpty()) throw new ResourseNotFoundException("Aluno nao encontrado");

            return alunoRepository.findById(id);
        }
    }

    @Override
    public void salva(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    @Override
    public void delete(Integer id) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente no delete");
        }
        alunoRepository.deleteById(id);
    }

    private boolean resourceNotFound(Integer id) {
        return alunoRepository.findAll().stream().filter(a -> a.getId() == id).findFirst().isEmpty();
    }

    @Override
    public void atualiza(Integer id, Aluno aluno) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente na atualização");
        }

        aluno.setId(id);
        alunoRepository.save(aluno);
    }

    @Override
    public List<Aluno> filter(AlunoFilters alunoFilters) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Aluno> cq = cb.createQuery(Aluno.class);
        Root<Aluno> aluno = cq.from(Aluno.class);
        List<Predicate> predicates = new ArrayList<>();

        if (alunoFilters.getNome().isPresent()) {
            String query= alunoFilters.getNome() + "%";
            Predicate nome = cb.like(aluno.get("nome"), query);
            predicates.add(nome);
        }

        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(predicates.toArray(Predicate[]::new));

        List<Aluno> resultList = entityManager.createQuery(cq).getResultList();


        return resultList;
    }
}
