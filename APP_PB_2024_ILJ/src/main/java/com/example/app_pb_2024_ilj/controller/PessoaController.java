package com.example.app_pb_2024_ilj.controller;

import com.example.app_pb_2024_ilj.model.Pessoa;
import com.example.app_pb_2024_ilj.payLoad.MessagePayLoad;
import com.example.app_pb_2024_ilj.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll(@RequestParam(required = false) Optional<String> nome){

        if(nome.isEmpty()){
            return ResponseEntity.ok(pessoaService.getAll());
        }else {
            List<Pessoa> pessoas = pessoaService.filter(nome.get());
            if (pessoas.isEmpty()){
                return ResponseEntity.notFound().build();

            }else{
                return ResponseEntity.ok(pessoas);
            }
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Integer id){
        try {

            Pessoa pLocalizada = pessoaService.getById(id);
            return ResponseEntity.ok().body(pLocalizada);
        } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/inclui")
    public void save(@RequestBody Pessoa pessoa){
        pessoaService.save(pessoa);
    }

    @PutMapping("/atualiza/{id}")
    public ResponseEntity<MessagePayLoad> update(@PathVariable Integer id, @RequestBody Pessoa p){
        try {
            pessoaService.update(id, p);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayLoad(ex.getMessage()));
        }
        return null;
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<MessagePayLoad> delete(@PathVariable Integer id){
        try {
            pessoaService.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new MessagePayLoad("Deletado com sucesso"));
        }catch (IllegalArgumentException ex){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayLoad(ex.getMessage()));
        }

    }
}
