package com.example.servicios.controller;

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

import com.example.servicios.entity.Servicio;
import com.example.servicios.service.ServicioService;

/**
 * CAPA CONTROLLER
 * Todas las rutas empiezan con /api/servicios
 */
@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Servicio> lista = servicioService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Servicio servicio = servicioService.buscarPorId(id);
        if (servicio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un servicio con id " + id);
        }
        return ResponseEntity.ok(servicio);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Servicio servicio) {
        String error = servicioService.validar(servicio);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Servicio creado = servicioService.crear(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Servicio servicio) {
        if (servicioService.buscarPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un servicio con id " + id);
        }
        String error = servicioService.validar(servicio);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Servicio actualizado = servicioService.actualizar(id, servicio);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = servicioService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un servicio con id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}