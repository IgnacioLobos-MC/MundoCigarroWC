package com.mundocigarro.ruta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rutas")
@Data
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    private String nombreRuta;
    private String origen;
    private String destino;
    private Double distanciaKm;
    private Integer tiempoEstimado;
    private String estado;
    private Long idDelivery;

}
