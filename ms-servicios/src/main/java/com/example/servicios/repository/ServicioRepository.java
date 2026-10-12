package com.example.servicios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicios.entity.Servicio;

/**
 * CAPA REPOSITORY
 */
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

}