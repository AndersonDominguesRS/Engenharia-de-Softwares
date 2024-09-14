package com.example.microsser_cadastro_pb_204.rabbitmq;

import com.example.microsser_cadastro_pb_204.exception.ResourseNotFoundException;
import com.example.microsser_cadastro_pb_204.model.Cadastro;
import com.example.microsser_cadastro_pb_204.model.Disciplina;
import com.example.microsser_cadastro_pb_204.service.CadastroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
@Component
public class DisciplinaConsumer {

    private final CadastroService cadastroService;

    @RabbitListener(queues = {"altera-disciplina-queue"}  )
    public void receiveDisciplina(@Payload String message ) throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        Disciplina disciplina = objectMapper.readValue(message, Disciplina.class);

        if(disciplina.getId() < 0) {
            throw new ResourseNotFoundException("ID inválido");
        }else {
            Optional <Cadastro> cadastroOpt=cadastroService.listaCadastro().stream().
                    filter(a -> a.getId_disciplina() == disciplina.getId()).findFirst();

            if (cadastroOpt.isEmpty()) throw new ResourseNotFoundException("Sem registro da Disciplina");

            Cadastro cadastro = cadastroOpt.get();

            log.info("VALOR INICIAL: {}", cadastro);
            cadastro.setId(cadastroOpt.get().getId());
            cadastro.setNomeDisciplina(disciplina.getNome());

            cadastroService.atualizarCadastro(cadastro);

            log.info("VALOR FINAL: {}", cadastro);
        }

    }
}
