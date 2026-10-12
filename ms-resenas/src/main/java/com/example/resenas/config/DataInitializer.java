package com.example.resenas.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.resenas.entity.Resena;
import com.example.resenas.repository.ResenaRepository;

/**
 * Carga resenas de ejemplo solo la primera vez que arranca el microservicio.
 * Los barberos con id 1, 2 y 3 corresponden a los datos de ms-usuarios.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatos(ResenaRepository resenaRepository) {
        return args -> {
            if (resenaRepository.count() > 0) {
                return;
            }
            resenaRepository.save(new Resena(1L, 1L, 5, "Excelente corte, quede muy conforme", LocalDate.of(2026, 12, 10)));
            resenaRepository.save(new Resena(2L, 2L, 4, "Buen trabajo con la barba", LocalDate.of(2026, 12, 10)));
            resenaRepository.save(new Resena(1L, 3L, 5, "Volvere seguro", LocalDate.of(2026, 12, 11)));
        };
    }
}