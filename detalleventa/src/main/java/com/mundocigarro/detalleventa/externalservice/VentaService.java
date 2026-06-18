package com.mundocigarro.detalleventa.externalservice;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mundocigarro.detalleventa.dto.VentaDto;

@Service
public class VentaService {

    private final WebClient webClient;

    public VentaService(WebClient webClient) {
        this.webClient = webClient;
    }

    public VentaDto obtenerVenta(Long idVenta){

        try {

            return webClient.get()
                    .uri("http://localhost:8083/api/v1/ventas/" + idVenta)
                    .retrieve()
                    .bodyToMono(VentaDto.class)
                    .block();

        } catch(Exception e){

            System.out.println(
                "Error al obtener venta desde el microservicio Venta: "
                + e.getMessage());

            return null;
        }
    }
}