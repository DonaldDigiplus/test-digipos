package com.devsdg.digipos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DigiposApplication implements CommandLineRunner {

    @Bean
    public BCryptPasswordEncoder getBcp() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext applicationContext() {
        return new SpringApplicationContext();
    }

    public static void main(String[] args) {
        SpringApplication.run(DigiposApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}

