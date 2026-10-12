package com.example.api_barberia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_barberia.model.Cita;

/**
 * CAPA REPOSITORY
 * Igual que BarberoRepository, pero para la tabla citas.
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
