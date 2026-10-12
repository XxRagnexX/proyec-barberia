package com.example.pagos.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * CAPA ENTITY
 * Representa la tabla "pagos" de la base de datos de ms-pagos.
 * El pago se relaciona con la cita solo por id (citaId), sin relacion JPA
 * con otros microservicios.
 */
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    // Id de la cita en ms-citas (referencia por id)
    @Column(name = "id_cita", nullable = false)
    private Long citaId;

    @Column(nullable = false)
    private Integer monto;

    // Valores esperados: EFECTIVO, TARJETA, TRANSFERENCIA
    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    // Valores esperados: PENDIENTE, PAGADO, ANULADO
    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    public Pago() {
    }

    public Pago(Long citaId, Integer monto, String metodoPago, String estado, LocalDateTime fechaPago) {
        this.citaId = citaId;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = estado;
        this.fechaPago = fechaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}