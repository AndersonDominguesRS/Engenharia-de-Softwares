package com.example.disciplina.rabbitmq;

import com.example.disciplina.model.Disciplina;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisciplinaProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void send(Disciplina disciplina) throws JsonProcessingException {
        log.info("Disciplina alterada: {}", disciplina);
        amqpTemplate.convertAndSend("altera-disciplina-exc", "altera-disciplina-rk", objectMapper.writeValueAsString(disciplina));
    }

}
