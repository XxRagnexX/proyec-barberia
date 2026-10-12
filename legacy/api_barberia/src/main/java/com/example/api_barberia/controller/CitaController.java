package com.example.api_barberia.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_barberia.model.Cita;
import com.example.api_barberia.service.CitaService;

/**
 * CAPA CONTROLLER
 * Recibe las peticiones HTTP (las que se envian desde Postman),
 * llama al service y responde con el codigo HTTP correcto.
 *
 * Todas las rutas empiezan con /api/citas
 */
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    // GET /api/citas -> lista todos (200 OK)
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Cita> lista = citaService.listar();
        return ResponseEntity.ok(lista);
    }

    // GET /api/citas/1 -> busca por id (200 OK o 404 Not Found)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Cita cita = citaService.buscarPorId(id);
        if (cita == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe una cita con id " + id);
        }
        return ResponseEntity.ok(cita);
    }

    // POST /api/citas -> crea uno nuevo (201 Created o 400 Bad Request)
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cita cita) {
        String error = citaService.validar(cita);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Cita creado = citaService.crear(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // PUT /api/citas/1 -> actualiza (200 OK, 404 Not Found o 400 Bad Request)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        if (citaService.buscarPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe una cita con id " + id);
        }
        String error = citaService.validar(cita);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Cita actualizado = citaService.actualizar(id, cita);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE /api/citas/1 -> elimina (204 No Content o 404 Not Found)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = citaService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe una cita con id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}
