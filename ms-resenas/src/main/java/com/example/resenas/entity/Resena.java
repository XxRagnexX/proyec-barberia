package com.example.resenas.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * CAPA ENTITY
 * Representa la tabla "resenas" de la base de datos de ms-resenas.
 * La resena se relaciona con el barbero y la cita solo por id,
 * sin relaciones JPA con otros microservicios.
 */
@Entity
@Table(name = "resenas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Long id;

    // Id del barbero en ms-usuarios (referencia por id)
    @Column(name = "id_barbero", nullable = false)
    private Long barberoId;

    // Id de la cita en ms-citas (opcional)
    @Column(name = "id_cita")
    private Long citaId;

    // Calificacion de 1 a 5 estrellas
    @Column(nullable = false)
    private Integer calificacion;

    @Column(length = 1000)
    private String comentario;

    @Column(name = "fecha")
    private LocalDate fecha;

    public Resena() {
    }

    public Resena(Long barberoId, Long citaId, Integer calificacion, String comentario, LocalDate fecha) {
        this.barberoId = barberoId;
        this.citaId = citaId;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBarberoId() {
        return barberoId;
    }

    public void setBarberoId(Long barberoId) {
        this.barberoId = barberoId;
    }

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}