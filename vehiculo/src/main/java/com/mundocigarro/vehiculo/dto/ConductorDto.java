package com.mundocigarro.vehiculo.dto;

import lombok.Data;

@Data
public class ConductorDto {

     private Long idConductor;
    private String nombre;
    private String apellido;
    private String rut;
    private String telefono;
    private String licencia;
    private String estado;


}
