package com.example.citas.controller;

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
        rutas.put("Listar citas", "GET    /api/citas");
        rutas.put("Buscar cita", "GET    /api/citas/{id}");
        rutas.put("Crear cita", "POST   /api/citas");
        rutas.put("Modificar cita", "PUT    /api/citas/{id}");
        rutas.put("Eliminar cita", "DELETE /api/citas/{id}");
        rutas.put("Ver base de datos", "http://localhost:8082/h2-console");

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("microservicio", "ms-citas");
        respuesta.put("puerto", 8082);
        respuesta.put("rutas", rutas);
        return ResponseEntity.ok(respuesta);
    }
}
