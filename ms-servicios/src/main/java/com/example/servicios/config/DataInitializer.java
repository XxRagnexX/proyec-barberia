package com.example.servicios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.servicios.entity.Servicio;
import com.example.servicios.repository.ServicioRepository;

/**
 * Carga servicios de ejemplo solo la primera vez que arranca el microservicio.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatos(ServicioRepository servicioRepository) {
        return args -> {
            if (servicioRepository.count() > 0) {
                return;
            }
            servicioRepository.save(new Servicio("Corte fade", "Degradado moderno con maquina", 12000, 30));
            servicioRepository.save(new Servicio("Perfilado de barba", "Recorte y delineado de barba", 7000, 20));
            servicioRepository.save(new Servicio("Corte clasico", "Corte tradicional con tijera", 10000, 30));
        };
    }
}