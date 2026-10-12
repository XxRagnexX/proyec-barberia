package com.example.pagos.controller;

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
        rutas.put("Listar pagos", "GET    /api/pagos");
        rutas.put("Buscar pago", "GET    /api/pagos/{id}");
        rutas.put("Crear pago", "POST   /api/pagos");
        rutas.put("Modificar pago", "PUT    /api/pagos/{id}");
        rutas.put("Eliminar pago", "DELETE /api/pagos/{id}");
        rutas.put("Ver base de datos", "http://localhost:8084/h2-console");

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("microservicio", "ms-pagos");
        respuesta.put("puerto", 8084);
        respuesta.put("rutas", rutas);
        return ResponseEntity.ok(respuesta);
    }
}