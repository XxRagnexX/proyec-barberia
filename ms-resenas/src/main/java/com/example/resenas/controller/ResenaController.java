package com.example.resenas.controller;

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

import com.example.resenas.entity.Resena;
import com.example.resenas.service.ResenaService;

/**
 * CAPA CONTROLLER
 * Todas las rutas empiezan con /api/resenas
 */
@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Resena> lista = resenaService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Resena resena = resenaService.buscarPorId(id);
        if (resena == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe una resena con id " + id);
        }
        return ResponseEntity.ok(resena);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Resena resena) {
        String error = resenaService.validar(resena);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Resena creado = resenaService.crear(resena);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Resena resena) {
        if (resenaService.buscarPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe una resena con id " + id);
        }
        String error = resenaService.validar(resena);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Resena actualizado = resenaService.actualizar(id, resena);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = resenaService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe una resena con id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}