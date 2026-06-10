package com.mundocigarro.delivery.externalservice;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mundocigarro.delivery.dto.VentaDto;

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
            return null;
        }
    }
}