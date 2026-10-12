package com.example.citas.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * CAPA ENTITY
 * Representa la tabla "citas" de la base de datos de ms-citas.
 *
 * En microservicios no se usan relaciones JPA entre servicios distintos:
 * el barbero y el servicio se referencian solo por su id (barberoId, servicioId).
 */
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long id;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    // Nombre del servicio (ej: "Corte fade")
    @Column(nullable = false)
    private String servicio;

    // Id del servicio en ms-servicios (opcional, sin validacion cruzada)
    @Column(name = "id_servicio")
    private Long servicioId;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Integer precio;

    // Id del barbero en ms-usuarios (referencia por id, no por relacion JPA)
    @Column(name = "id_barbero", nullable = false)
    private Long barberoId;

    public Cita() {
    }

    public Cita(String nombreCliente, String servicio, Long servicioId, LocalDateTime fechaHora, Integer precio,
            Long barberoId) {
        this.nombreCliente = nombreCliente;
        this.servicio = servicio;
        this.servicioId = servicioId;
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.barberoId = barberoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Long getBarberoId() {
        return barberoId;
    }

    public void setBarberoId(Long barberoId) {
        this.barberoId = barberoId;
    }
}
