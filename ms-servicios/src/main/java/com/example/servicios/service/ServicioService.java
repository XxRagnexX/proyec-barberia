package com.example.servicios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.servicios.entity.Servicio;
import com.example.servicios.repository.ServicioRepository;

/**
 * CAPA SERVICE
 * Logica de los servicios de barberia: validar datos y guardar.
 */
@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<Servicio> listar() {
        return servicioRepository.findAll();
    }

    public Servicio buscarPorId(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    // Devuelve un mensaje de error, o null si todo esta bien
    public String validar(Servicio servicio) {
        if (servicio.getNombre() == null || servicio.getNombre().isBlank()) {
            return "El nombre es obligatorio";
        }
        if (servicio.getPrecio() == null || servicio.getPrecio() <= 0) {
            return "El precio es obligatorio y debe ser mayor a 0";
        }
        if (servicio.getDuracionMinutos() != null && servicio.getDuracionMinutos() <= 0) {
            return "La duracion debe ser mayor a 0 minutos";
        }
        return null;
    }

    public Servicio crear(Servicio servicio) {
        servicio.setId(null);
        return servicioRepository.save(servicio);
    }

    // Devuelve el servicio actualizado, o null si no existe
    public Servicio actualizar(Long id, Servicio datosNuevos) {
        Servicio existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setNombre(datosNuevos.getNombre());
        existente.setDescripcion(datosNuevos.getDescripcion());
        existente.setPrecio(datosNuevos.getPrecio());
        existente.setDuracionMinutos(datosNuevos.getDuracionMinutos());
        return servicioRepository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!servicioRepository.existsById(id)) {
            return false;
        }
        servicioRepository.deleteById(id);
        return true;
    }
}