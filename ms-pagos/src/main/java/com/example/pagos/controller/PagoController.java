package com.example.pagos.controller;

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

import com.example.pagos.entity.Pago;
import com.example.pagos.service.PagoService;

/**
 * CAPA CONTROLLER
 * Todas las rutas empiezan con /api/pagos
 */
@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Pago> lista = pagoService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Pago pago = pagoService.buscarPorId(id);
        if (pago == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un pago con id " + id);
        }
        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pago pago) {
        String error = pagoService.validar(pago);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Pago creado = pagoService.crear(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        if (pagoService.buscarPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un pago con id " + id);
        }
        String error = pagoService.validar(pago);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        Pago actualizado = pagoService.actualizar(id, pago);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        boolean eliminado = pagoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un pago con id " + id);
        }
        return ResponseEntity.noContent().build();
    }
}