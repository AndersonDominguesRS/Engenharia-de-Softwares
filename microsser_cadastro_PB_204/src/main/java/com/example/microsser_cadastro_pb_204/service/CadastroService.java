package com.example.microsser_cadastro_pb_204.service;

import com.example.microsser_cadastro_pb_204.CadastroRepository;
import com.example.microsser_cadastro_pb_204.exception.ResourseNotFoundException;
import com.example.microsser_cadastro_pb_204.feign.AlunoClient;
import com.example.microsser_cadastro_pb_204.feign.DisciplinaClient;
import com.example.microsser_cadastro_pb_204.model.Aluno;
import com.example.microsser_cadastro_pb_204.model.Cadastro;
import com.example.microsser_cadastro_pb_204.model.Disciplina;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CadastroService {

    private final AlunoClient alunoClient;
    private final DisciplinaClient disciplinaClient;
    @Autowired
    private CadastroRepository cadastroRepository;

    public List<Cadastro> listaCadastro() {
        return cadastroRepository.findAll();
    }

    public Aluno alunoGetById(Integer id){
        return alunoClient.ListAlunoById(id);
    }

    public List<Aluno> listaAlunos(){
        return alunoClient.ListAluno();
    }

    public Disciplina disciplinaGetById(Integer id){
        return disciplinaClient.ListDisciplinaById(id);
    }

    public List<Disciplina> listaDisciplinas(){
        return disciplinaClient.ListDisciplina();
    }

    public Cadastro novaMatricula(Integer id_aluno, Integer id_disciplina){
        Cadastro cadastro = new Cadastro();

        if (id_aluno < 0 && id_disciplina<0) {
            throw new ResourseNotFoundException("ID Inválido");
        } else {
            Optional<Disciplina> disciplinaOpt = listaDisciplinas().stream().filter(a -> a.getId() == id_disciplina).findFirst();

            Optional<Aluno> alunoOpt = listaAlunos().stream().filter(a -> a.getId() == id_aluno).findFirst();

            Optional<Cadastro> cadastroOptDisciplina = listaCadastro().stream().filter(a -> a.getId() == id_disciplina).findFirst();
            Optional<Cadastro> cadastroOptAluno = listaCadastro().stream().filter(a -> a.getId() == id_aluno).findFirst();

            if (cadastroOptDisciplina.isPresent() && cadastroOptAluno.isPresent())
                throw new ResourseNotFoundException("Aluno já cadastro nesta Disciplina");

            if (disciplinaOpt.isEmpty()) throw new ResourseNotFoundException("Disciplina nao encontrado");

            if (alunoOpt.isEmpty()) throw new ResourseNotFoundException("Aluno nao encontrado");

            log.info("Disciplina: {} ", disciplinaGetById(id_disciplina).getNome());

            cadastro.setId_aluno(disciplinaGetById(id_aluno).getId());
            cadastro.setNomeAluno(alunoGetById(id_aluno).getNome());
            cadastro.setId_disciplina(disciplinaGetById(id_disciplina).getId());
            cadastro.setNomeDisciplina(disciplinaGetById(id_disciplina).getNome());

            cadastroRepository.save(cadastro);

            return cadastro;
        }

//        if (alunoGetById(id_aluno) )
//        Aluno aluno= new Aluno();
//        aluno=alunoGetById(id_aluno);
//
//        Disciplina escola= new Disciplina();
//        escola=escolaGetById(id_escola);
//
//        return cadastroRepository.save(cadastro);
    }

//        RestClient restClient = RestClient.create();
//        var serverUrl= String.format("http://localhost:8081/aluno/%s", id);
//        NovoAluno aluno = restClient.get()
//                .uri(serverUrl)
//                .retrieve()
//                .toEntity(NovoAluno.class).getBody();
//
//        return aluno;
//    }


}
