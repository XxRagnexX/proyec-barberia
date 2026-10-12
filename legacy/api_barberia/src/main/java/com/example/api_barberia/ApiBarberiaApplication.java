package com.example.api_barberia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de partida de la aplicacion.
 * Al ejecutar este main se enciende el servidor en http://localhost:8080
 * (desde VS Code o IntelliJ: abrir este archivo y presionar "Run").
 */
@SpringBootApplication
public class ApiBarberiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBarberiaApplication.class, args);
	}

}
