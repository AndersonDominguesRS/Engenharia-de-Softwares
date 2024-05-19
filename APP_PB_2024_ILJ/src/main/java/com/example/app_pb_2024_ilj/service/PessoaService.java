package com.example.app_pb_2024_ilj.service;

import com.example.app_pb_2024_ilj.exception.ResourceNotFoundException;
import com.example.app_pb_2024_ilj.model.Pessoa;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    static List<Pessoa> pessoas = initValues();

    private static List<Pessoa> initValues() {
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.add(new Pessoa(0, "Anderson"));
        pessoas.add(new Pessoa(1, "Isabelly"));

        return pessoas;
    }

    public Pessoa getById(int id){
        if (id <0){
            throw new IllegalArgumentException("Valor inválido");
        }
        return pessoas.get(id);
    }

    public void save(Pessoa pessoa){
        pessoas.add(pessoa);
    }

    public void delete(int id){

        pessoas.remove(id);
    }
    public void update(Integer id, Pessoa pessoa){
        if(resourceNotFound(id)){
            throw new ResourceAccessException("Pessoa não localizada");
        }
        pessoas.set(id, pessoa);

    }
    public List<Pessoa> getAll(){
        return pessoas;
    }

    public List<Pessoa> filter(String nome){
        List<Pessoa> pAll= getAll();
        List<Pessoa> result= pAll.stream().filter(pessoa -> pessoa.getNome().startsWith(nome)).toList();
        return result;
    }

    public void deleteById(Integer id) {
        if (resourceNotFound(id)){
            throw new ResourceAccessException("Pessoa não encontrada");
        }
        pessoas.remove(pessoas.get(id));
    }

    private static boolean resourceNotFound(Integer id) {
        return pessoas.stream().filter(pessoa -> pessoa.getId() == id).findFirst().isEmpty();
    }
}
