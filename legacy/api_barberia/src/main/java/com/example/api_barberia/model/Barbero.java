package com.example.api_barberia.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * CAPA MODEL
 * Esta clase representa la tabla "barberos" de la base de datos.
 * Cada atributo es una columna.
 *
 * Relacion: UN barbero tiene MUCHAS citas (1 a N).
 */
@Entity
@Table(name = "barberos")
public class Barbero {

    // Clave primaria: la base de datos genera el numero solo (1, 2, 3...)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_barbero")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String especialidad;

    @Column(nullable = false)
    private String telefono;

    // Lado "1" de la relacion. "mappedBy" indica que la clave foranea esta en Cita.
    // cascade ALL: si se elimina el barbero, tambien se eliminan sus citas.
    // @JsonIgnore: no se muestran las citas dentro del barbero (evita un ciclo infinito en el JSON).
    @JsonIgnore
    @OneToMany(mappedBy = "barbero", cascade = CascadeType.ALL)
    private List<Cita> citas = new ArrayList<>();

    // ---------- Getters y Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
