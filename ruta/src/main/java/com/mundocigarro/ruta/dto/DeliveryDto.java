package com.mundocigarro.ruta.dto;

import java.time.LocalDate;

public class DeliveryDto {

    private Long idDelivery;
    private Long idVenta;
    private String direccion;
    private String comuna;
    private String region;
    private String estado;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;

    public DeliveryDto() {
    }

    public DeliveryDto(Long idDelivery, Long idVenta, String direccion,
        String comuna, String region, String estado,
        LocalDate fechaEnvio, LocalDate fechaEntrega) {
        this.idDelivery = idDelivery;
        this.idVenta = idVenta;
        this.direccion = direccion;
        this.comuna = comuna;
        this.region = region;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
    }

    public Long getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
