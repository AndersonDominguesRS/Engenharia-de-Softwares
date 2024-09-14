package com.example.disciplina.impl;



import com.example.disciplina.exception.ResourseNotFoundException;
import com.example.disciplina.filters.DisciplinaFilters;
import com.example.disciplina.model.Disciplina;
import com.example.disciplina.rabbitmq.DisciplinaProducer;
import com.example.disciplina.repository.DisciplinaRepository;
import com.example.disciplina.service.DisciplinaService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class DisciplinaServiceImp implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final EntityManager entityManager;
    private final DisciplinaProducer disciplinaProducer;

    @Override
    public List<Disciplina> lista() {
        return disciplinaRepository.findAll();
    }

    @Override
    public Optional<Disciplina> aluinoId(Integer id) {
        if (id < 0) {
            throw new ResourseNotFoundException("ID inexistente");
        } else {
            Optional<Disciplina> disciplinaOpt = disciplinaRepository.findAll().stream().filter(a -> a.getId() == id).findFirst();

            if (disciplinaOpt.isEmpty()) throw new ResourseNotFoundException("Disciplina nao encontrado");

            return disciplinaRepository.findById(id);
        }
    }

    @Override
    public void salva(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    @Override
    public void delete(Integer id) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente no delete");
        }
        disciplinaRepository.deleteById(id);
    }

    private boolean resourceNotFound(Integer id) {
        return disciplinaRepository.findAll().stream().filter(a -> a.getId() == id).findFirst().isEmpty();
    }

    @Override
    public void atualiza(Integer id, Disciplina disciplina) throws JsonProcessingException {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente na atualização");
        }

        disciplina.setId(id);
        disciplinaProducer.send(disciplina);
        disciplinaRepository.save(disciplina);
    }

    @Override
    public List<Disciplina> filter(DisciplinaFilters disciplinaFilters) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Disciplina> cq = cb.createQuery(Disciplina.class);
        Root<Disciplina> disciplina = cq.from(Disciplina.class);
        List<Predicate> predicates = new ArrayList<>();

        if (disciplinaFilters.getNome().isPresent()) {
            String query= disciplinaFilters.getNome() + "%";
            Predicate nome = cb.like(disciplina.get("nome"), query);
            predicates.add(nome);
        }

        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(predicates.toArray(Predicate[]::new));

        List<Disciplina> resultList = entityManager.createQuery(cq).getResultList();


        return resultList;
    }
}
