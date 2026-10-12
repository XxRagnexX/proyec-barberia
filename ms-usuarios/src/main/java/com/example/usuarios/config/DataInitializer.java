package com.example.usuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.usuarios.entity.Barbero;
import com.example.usuarios.repository.BarberoRepository;

/**
 * Carga datos de ejemplo solo la primera vez que arranca el microservicio.
 * Al usar una base de datos de archivo, se revisa count() == 0 para no duplicar.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatos(BarberoRepository barberoRepository) {
        return args -> {
            if (barberoRepository.count() > 0) {
                return;
            }
            barberoRepository.save(new Barbero("Matias Rojas", "Fade y degradados", "+56911112222"));
            barberoRepository.save(new Barbero("Camila Soto", "Barba y perfilado", "+56933334444"));
            barberoRepository.save(new Barbero("Diego Fuentes", "Cortes clasicos", "+56955556666"));
        };
    }
}
