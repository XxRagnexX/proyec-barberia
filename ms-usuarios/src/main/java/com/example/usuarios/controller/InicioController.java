package com.example.usuarios.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Pagina de inicio: muestra las rutas disponibles y el puerto del servicio.
 */
@RestController
public class InicioController {

    @GetMapping("/")
    public ResponseEntity<?> inicio() {
        Map<String, String> rutas = new LinkedHashMap<>();
        rutas.put("Listar barberos", "GET    /api/barberos");
        rutas.put("Buscar barbero", "GET    /api/barberos/{id}");
        rutas.put("Crear barbero", "POST   /api/barberos");
        rutas.put("Modificar barbero", "PUT    /api/barberos/{id}");
        rutas.put("Eliminar barbero", "DELETE /api/barberos/{id}");
        rutas.put("Ver base de datos", "http://localhost:8081/h2-console");

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("microservicio", "ms-usuarios");
        respuesta.put("puerto", 8081);
        respuesta.put("rutas", rutas);
        return ResponseEntity.ok(respuesta);
    }
}
