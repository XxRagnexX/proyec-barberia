package com.example.resenas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de partida del microservicio de Resenas.
 * Se ejecuta en http://localhost:8085
 */
@SpringBootApplication
public class ResenasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResenasApplication.class, args);
	}

}