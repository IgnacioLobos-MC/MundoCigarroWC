package com.cigarro.gestioncigarro.dto;

import lombok.Data;

@Data
public class ProveedorDTO {

    private Long idProveedor;
    private String nombreProveedor;
    private String rutEmpresa;
    private String direccion;
    private String telefono;
    private String email;
}