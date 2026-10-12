package com.example.resenas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resenas.entity.Resena;

/**
 * CAPA REPOSITORY
 */
@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

}