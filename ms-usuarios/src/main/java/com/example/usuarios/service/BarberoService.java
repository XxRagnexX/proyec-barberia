package com.example.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuarios.entity.Barbero;
import com.example.usuarios.repository.BarberoRepository;

/**
 * CAPA SERVICE
 * Logica de los barberos: validar datos y pedirle al repository que guarde.
 */
@Service
public class BarberoService {

    private final BarberoRepository barberoRepository;

    public BarberoService(BarberoRepository barberoRepository) {
        this.barberoRepository = barberoRepository;
    }

    public List<Barbero> listar() {
        return barberoRepository.findAll();
    }

    // Devuelve el barbero o null si no existe
    public Barbero buscarPorId(Long id) {
        return barberoRepository.findById(id).orElse(null);
    }

    // Devuelve un mensaje de error, o null si todo esta bien
    public String validar(Barbero barbero) {
        if (barbero.getNombre() == null || barbero.getNombre().isBlank()) {
            return "El nombre es obligatorio";
        }
        if (barbero.getTelefono() == null || barbero.getTelefono().isBlank()) {
            return "El telefono es obligatorio";
        }
        if (barbero.getTelefono().length() < 8) {
            return "El telefono debe tener al menos 8 caracteres";
        }
        return null;
    }

    public Barbero crear(Barbero barbero) {
        barbero.setId(null);
        return barberoRepository.save(barbero);
    }

    // Devuelve el barbero actualizado, o null si no existe
    public Barbero actualizar(Long id, Barbero datosNuevos) {
        Barbero existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setNombre(datosNuevos.getNombre());
        existente.setEspecialidad(datosNuevos.getEspecialidad());
        existente.setTelefono(datosNuevos.getTelefono());
        return barberoRepository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!barberoRepository.existsById(id)) {
            return false;
        }
        barberoRepository.deleteById(id);
        return true;
    }
}
