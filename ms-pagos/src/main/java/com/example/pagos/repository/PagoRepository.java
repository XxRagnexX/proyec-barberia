package com.example.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pagos.entity.Pago;

/**
 * CAPA REPOSITORY
 */
@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

}