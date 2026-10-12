package com.example.resenas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.resenas.entity.Resena;
import com.example.resenas.repository.ResenaRepository;

/**
 * CAPA SERVICE
 * Logica de las resenas: validar datos y guardar.
 */
@Service
public class ResenaService {

    private final ResenaRepository resenaRepository;

    public ResenaService(ResenaRepository resenaRepository) {
        this.resenaRepository = resenaRepository;
    }

    public List<Resena> listar() {
        return resenaRepository.findAll();
    }

    public Resena buscarPorId(Long id) {
        return resenaRepository.findById(id).orElse(null);
    }

    // Devuelve un mensaje de error, o null si todo esta bien
    public String validar(Resena resena) {
        if (resena.getBarberoId() == null) {
            return "Debe indicar el barbero, ejemplo: \"barberoId\": 1";
        }
        if (resena.getCalificacion() == null || resena.getCalificacion() < 1 || resena.getCalificacion() > 5) {
            return "La calificacion debe estar entre 1 y 5";
        }
        return null;
    }

    public Resena crear(Resena resena) {
        resena.setId(null);
        return resenaRepository.save(resena);
    }

    // Devuelve la resena actualizada, o null si no existe
    public Resena actualizar(Long id, Resena datosNuevos) {
        Resena existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setBarberoId(datosNuevos.getBarberoId());
        existente.setCitaId(datosNuevos.getCitaId());
        existente.setCalificacion(datosNuevos.getCalificacion());
        existente.setComentario(datosNuevos.getComentario());
        existente.setFecha(datosNuevos.getFecha());
        return resenaRepository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!resenaRepository.existsById(id)) {
            return false;
        }
        resenaRepository.deleteById(id);
        return true;
    }
}