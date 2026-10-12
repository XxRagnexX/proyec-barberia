package com.example.servicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de partida del microservicio de Servicios.
 * Se ejecuta en http://localhost:8083
 */
@SpringBootApplication
public class ServiciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosApplication.class, args);
	}

}