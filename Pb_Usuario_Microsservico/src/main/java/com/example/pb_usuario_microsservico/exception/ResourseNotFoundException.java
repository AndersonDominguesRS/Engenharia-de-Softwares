package com.example.pb_usuario_microsservico.exception;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String mensagem) {
        super(mensagem);
    }
}
