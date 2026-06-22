package com.mundocigarro.conductor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "conductores")
@Data
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConductor;

    private String nombre;
    private String apellido;
    private String rut;
    private String telefono;
    private String licencia;
    private String estado;

}
