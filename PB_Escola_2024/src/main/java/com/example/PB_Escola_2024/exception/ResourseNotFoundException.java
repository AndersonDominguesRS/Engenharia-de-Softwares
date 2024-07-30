package com.example.PB_Escola_2024.exception;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String mensagem) {
        super(mensagem);
    }
}
