package com.example.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.usuarios.entity.Barbero;

/**
 * CAPA REPOSITORY
 * JpaRepository ya trae findAll(), findById(id), save(), deleteById()...
 */
@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {

}
