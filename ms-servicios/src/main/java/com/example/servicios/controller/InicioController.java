package com.example.servicios.controller;

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
        rutas.put("Listar servicios", "GET    /api/servicios");
        rutas.put("Buscar servicio", "GET    /api/servicios/{id}");
        rutas.put("Crear servicio", "POST   /api/servicios");
        rutas.put("Modificar servicio", "PUT    /api/servicios/{id}");
        rutas.put("Eliminar servicio", "DELETE /api/servicios/{id}");
        rutas.put("Ver base de datos", "http://localhost:8083/h2-console");

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("microservicio", "ms-servicios");
        respuesta.put("puerto", 8083);
        respuesta.put("rutas", rutas);
        return ResponseEntity.ok(respuesta);
    }
}