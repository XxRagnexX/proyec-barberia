package com.example.citas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.citas.entity.Cita;
import com.example.citas.repository.CitaRepository;

/**
 * CAPA SERVICE
 * Logica de las citas: validar datos y guardar.
 * No consulta otros microservicios: el barbero se guarda solo por su id.
 */
@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    public Cita buscarPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    // Devuelve un mensaje de error, o null si todo esta bien
    public String validar(Cita cita) {
        if (cita.getNombreCliente() == null || cita.getNombreCliente().isBlank()) {
            return "El nombre del cliente es obligatorio";
        }
        if (cita.getServicio() == null || cita.getServicio().isBlank()) {
            return "El servicio es obligatorio";
        }
        if (cita.getFechaHora() == null) {
            return "La fecha y hora es obligatoria (ejemplo: 2026-12-20T15:30:00)";
        }
        if (cita.getPrecio() == null || cita.getPrecio() <= 0) {
            return "El precio es obligatorio y debe ser mayor a 0";
        }
        if (cita.getBarberoId() == null) {
            return "Debe indicar el barbero, ejemplo: \"barberoId\": 1";
        }
        return null;
    }

    public Cita crear(Cita cita) {
        cita.setId(null);
        return citaRepository.save(cita);
    }

    // Devuelve la cita actualizada, o null si no existe
    public Cita actualizar(Long id, Cita datosNuevos) {
        Cita existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setNombreCliente(datosNuevos.getNombreCliente());
        existente.setServicio(datosNuevos.getServicio());
        existente.setServicioId(datosNuevos.getServicioId());
        existente.setFechaHora(datosNuevos.getFechaHora());
        existente.setPrecio(datosNuevos.getPrecio());
        existente.setBarberoId(datosNuevos.getBarberoId());
        return citaRepository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!citaRepository.existsById(id)) {
            return false;
        }
        citaRepository.deleteById(id);
        return true;
    }
}
