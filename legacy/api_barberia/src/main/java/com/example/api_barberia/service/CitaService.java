package com.example.api_barberia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api_barberia.model.Barbero;
import com.example.api_barberia.model.Cita;
import com.example.api_barberia.repository.BarberoRepository;
import com.example.api_barberia.repository.CitaRepository;

/**
 * CAPA SERVICE
 * Logica de las citas: validar datos y conectar cada cita con su barbero.
 */
@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final BarberoRepository barberoRepository;

    public CitaService(CitaRepository citaRepository, BarberoRepository barberoRepository) {
        this.citaRepository = citaRepository;
        this.barberoRepository = barberoRepository;
    }

    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    // Devuelve la cita o null si no existe
    public Cita buscarPorId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    // Revisa los datos. Devuelve un mensaje de error, o null si todo esta bien.
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
        if (cita.getBarbero() == null || cita.getBarbero().getId() == null) {
            return "Debe indicar el barbero, ejemplo: \"barbero\": { \"id\": 1 }";
        }
        if (!barberoRepository.existsById(cita.getBarbero().getId())) {
            return "No existe un barbero con id " + cita.getBarbero().getId();
        }
        return null;
    }

    public Cita crear(Cita cita) {
        cita.setId(null);
        // Se busca el barbero completo en la base de datos para asociarlo a la cita
        Barbero barbero = barberoRepository.findById(cita.getBarbero().getId()).orElse(null);
        cita.setBarbero(barbero);
        return citaRepository.save(cita);
    }

    // Devuelve la cita actualizada, o null si no existe
    public Cita actualizar(Long id, Cita datosNuevos) {
        Cita existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        Barbero barbero = barberoRepository.findById(datosNuevos.getBarbero().getId()).orElse(null);
        existente.setNombreCliente(datosNuevos.getNombreCliente());
        existente.setServicio(datosNuevos.getServicio());
        existente.setFechaHora(datosNuevos.getFechaHora());
        existente.setPrecio(datosNuevos.getPrecio());
        existente.setBarbero(barbero);
        return citaRepository.save(existente);
    }

    // Devuelve true si se elimino, false si no existia
    public boolean eliminar(Long id) {
        if (!citaRepository.existsById(id)) {
            return false;
        }
        citaRepository.deleteById(id);
        return true;
    }
}
