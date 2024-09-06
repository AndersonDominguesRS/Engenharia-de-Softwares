package com.example.pb_escola_mircosservico.impl;


import com.example.pb_escola_mircosservico.exception.ResourseNotFoundException;
import com.example.pb_escola_mircosservico.filters.EscolaFilters;
import com.example.pb_escola_mircosservico.model.Aluno;
import com.example.pb_escola_mircosservico.model.Escola;
import com.example.pb_escola_mircosservico.repository.EscolaRepository;
import com.example.pb_escola_mircosservico.service.EscolaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EscolaServiceImpl implements EscolaService {

    private final EscolaRepository escolaRepository;
    private final EntityManager entityManager;

    @Override
    public List<Escola> lista() {
        return escolaRepository.findAll();
    }



    @Override
    public List<Escola> findAll(int page, int size, boolean ascending) {
        Sort order=ascending ? Sort.by("nome").ascending() : Sort.by("nome").descending();
        PageRequest pageRequest = PageRequest.of(page, size, order);
        return escolaRepository.findAll(pageRequest).stream().toList();
    }


    @Override
    public List<Escola> listaNome(String nome) {
        return escolaRepository.findAllByNome(nome);
    }

    @Override
    public Optional<Escola> listaId(Integer id) {
        if (id < 0) {
            throw new ResourseNotFoundException("ID inexistente");
        } else {
            Optional<Escola> escolaOpt = escolaRepository.findAll().stream().filter(a -> a.getId() == id).findFirst();

            if (escolaOpt.isEmpty()) throw new ResourseNotFoundException("Escola nao encontrado");

            return escolaRepository.findById(id);
        }
    }

    @Override
    public void salva(Escola escola) {
        escolaRepository.save(escola);
    }

    @Override
    public void delete(Integer id) {

        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente no delete");
        }
        escolaRepository.deleteById(id);
    }

    private boolean resourceNotFound(Integer id) {
        return escolaRepository.findAll().stream().filter(a -> a.getId() == id).findFirst().isEmpty();
    }

    @Override
    public void atualiza(Integer id, Escola escola) {



        if(resourceNotFound(id)){
            throw new ResourseNotFoundException("ID inexistente na atualização");
        }

        escola.setId(id);
        escolaRepository.save(escola);
    }

    @Override
    public List<Escola> filter(EscolaFilters escolaFilters) {
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Escola> cq = cb.createQuery(Escola.class);
        Root<Escola> escola = cq.from(Escola.class);
        List<Predicate> predicates = new ArrayList<>();

        if (escolaFilters.getNome().isPresent()) {
            String query= escolaFilters.getNome() + "%";
            Predicate nome = cb.like(escola.get("nome"), query);
            predicates.add(nome);
        }

        Predicate[] array = predicates.toArray(Predicate[]::new);
        cq.where(predicates.toArray(Predicate[]::new));

        List<Escola> resultList = entityManager.createQuery(cq).getResultList();


        return resultList;
    }
}
