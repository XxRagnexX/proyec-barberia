package com.example.pagos.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.pagos.entity.Pago;
import com.example.pagos.repository.PagoRepository;

/**
 * Carga pagos de ejemplo solo la primera vez que arranca el microservicio.
 * Las citas con id 1, 2 y 3 corresponden a los datos de ms-citas.
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner cargarDatos(PagoRepository pagoRepository) {
        return args -> {
            if (pagoRepository.count() > 0) {
                return;
            }
            pagoRepository.save(new Pago(1L, 12000, "EFECTIVO", "PAGADO",
                    LocalDateTime.of(2026, 12, 10, 10, 5)));
            pagoRepository.save(new Pago(2L, 7000, "TARJETA", "PENDIENTE",
                    LocalDateTime.of(2026, 12, 10, 11, 10)));
            pagoRepository.save(new Pago(3L, 10000, "TRANSFERENCIA", "PAGADO",
                    LocalDateTime.of(2026, 12, 11, 12, 15)));
        };
    }
}