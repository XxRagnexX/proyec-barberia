package com.example.citas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.citas.entity.Cita;

/**
 * CAPA REPOSITORY
 */
@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
