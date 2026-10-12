package com.example.citas.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.citas.entity.Cita;
import com.example.citas.repository.CitaRepository;

/**
 * Carga citas de ejemplo solo la primera vez que arranca el microservicio.
 * Los barberos con id 1, 2 y 3 corresponden a los datos de ms-usuarios.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatos(CitaRepository citaRepository) {
        return args -> {
            if (citaRepository.count() > 0) {
                return;
            }
            citaRepository.save(new Cita("Juan Perez", "Corte fade", 1L,
                    LocalDateTime.of(2026, 12, 10, 10, 0), 12000, 1L));
            citaRepository.save(new Cita("Pedro Munoz", "Perfilado de barba", 2L,
                    LocalDateTime.of(2026, 12, 10, 11, 0), 7000, 2L));
            citaRepository.save(new Cita("Tomas Diaz", "Corte clasico", 3L,
                    LocalDateTime.of(2026, 12, 11, 12, 0), 10000, 1L));
        };
    }
}
