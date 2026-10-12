package com.example.api_barberia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api_barberia.model.Barbero;
import com.example.api_barberia.repository.BarberoRepository;

/**
 * CAPA SERVICE
 * Aqui va la logica: validar datos y pedirle al repository que guarde, busque o elimine.
 */
@Service
public class BarberoService {

    private final BarberoRepository barberoRepository;

    // Inyeccion por constructor: Spring nos entrega el repository listo para usar
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

    // Revisa los datos. Devuelve un mensaje de error, o null si todo esta bien.
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
        barbero.setId(null); // el id lo genera la base de datos
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

    // Devuelve true si se elimino, false si no existia
    public boolean eliminar(Long id) {
        if (!barberoRepository.existsById(id)) {
            return false;
        }
        barberoRepository.deleteById(id);
        return true;
    }
}
