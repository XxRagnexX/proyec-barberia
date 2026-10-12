package com.example.pagos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pagos.entity.Pago;
import com.example.pagos.repository.PagoRepository;

/**
 * CAPA SERVICE
 * Logica de los pagos: validar datos y guardar.
 */
@Service
public class PagoService {

    private static final List<String> METODOS_VALIDOS = List.of("EFECTIVO", "TARJETA", "TRANSFERENCIA");
    private static final List<String> ESTADOS_VALIDOS = List.of("PENDIENTE", "PAGADO", "ANULADO");

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    public Pago buscarPorId(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

    // Devuelve un mensaje de error, o null si todo esta bien
    public String validar(Pago pago) {
        if (pago.getCitaId() == null) {
            return "Debe indicar la cita, ejemplo: \"citaId\": 1";
        }
        if (pago.getMonto() == null || pago.getMonto() <= 0) {
            return "El monto es obligatorio y debe ser mayor a 0";
        }
        if (pago.getMetodoPago() == null || !METODOS_VALIDOS.contains(pago.getMetodoPago())) {
            return "El metodo de pago debe ser EFECTIVO, TARJETA o TRANSFERENCIA";
        }
        if (pago.getEstado() == null || !ESTADOS_VALIDOS.contains(pago.getEstado())) {
            return "El estado debe ser PENDIENTE, PAGADO o ANULADO";
        }
        return null;
    }

    public Pago crear(Pago pago) {
        pago.setId(null);
        return pagoRepository.save(pago);
    }

    // Devuelve el pago actualizado, o null si no existe
    public Pago actualizar(Long id, Pago datosNuevos) {
        Pago existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setCitaId(datosNuevos.getCitaId());
        existente.setMonto(datosNuevos.getMonto());
        existente.setMetodoPago(datosNuevos.getMetodoPago());
        existente.setEstado(datosNuevos.getEstado());
        existente.setFechaPago(datosNuevos.getFechaPago());
        return pagoRepository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!pagoRepository.existsById(id)) {
            return false;
        }
        pagoRepository.deleteById(id);
        return true;
    }
}