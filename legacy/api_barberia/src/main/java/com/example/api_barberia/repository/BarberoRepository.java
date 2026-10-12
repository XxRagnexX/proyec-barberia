package com.example.api_barberia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_barberia.model.Barbero;

/**
 * CAPA REPOSITORY
 * Al extender JpaRepository ya tenemos listos, sin escribir SQL:
 * findAll(), findById(id), save(objeto), deleteById(id), existsById(id)...
 */
@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {

}
