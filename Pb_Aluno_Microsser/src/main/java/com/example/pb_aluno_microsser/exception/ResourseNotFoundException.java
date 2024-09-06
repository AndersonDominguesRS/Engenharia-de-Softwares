package com.example.pb_aluno_microsser.exception;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String mensagem) {
        super(mensagem);
    }
}
