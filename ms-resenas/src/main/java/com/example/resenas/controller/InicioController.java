package com.example.resenas.controller;

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
        rutas.put("Listar resenas", "GET    /api/resenas");
        rutas.put("Buscar resena", "GET    /api/resenas/{id}");
        rutas.put("Crear resena", "POST   /api/resenas");
        rutas.put("Modificar resena", "PUT    /api/resenas/{id}");
        rutas.put("Eliminar resena", "DELETE /api/resenas/{id}");
        rutas.put("Ver base de datos", "http://localhost:8085/h2-console");

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("microservicio", "ms-resenas");
        respuesta.put("puerto", 8085);
        respuesta.put("rutas", rutas);
        return ResponseEntity.ok(respuesta);
    }
}