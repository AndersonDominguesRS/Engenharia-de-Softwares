package com.example.microsser_cadastro_pb_204;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicrosserCadastroPb204Application {

    public static void main(String[] args) {
        SpringApplication.run(MicrosserCadastroPb204Application.class, args);
    }

}
