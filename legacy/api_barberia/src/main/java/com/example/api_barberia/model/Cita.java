package com.example.api_barberia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * CAPA MODEL
 * Esta clase representa la tabla "citas" de la base de datos.
 *
 * Relacion: MUCHAS citas pertenecen a UN barbero (N a 1).
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

    // Ej: "Corte fade", "Perfilado de barba"
    @Column(nullable = false)
    private String servicio;

    // Fecha y hora de la cita. En JSON se escribe asi: "2026-12-20T15:30:00"
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    // Precio en pesos chilenos
    @Column(nullable = false)
    private Integer precio;

    // Lado "N" de la relacion. Crea la columna id_barbero (clave foranea) en la tabla citas.
    @ManyToOne
    @JoinColumn(name = "id_barbero", nullable = false)
    private Barbero barbero;

    // ---------- Getters y Setters ----------

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

    public Barbero getBarbero() {
        return barbero;
    }

    public void setBarbero(Barbero barbero) {
        this.barbero = barbero;
    }
}
