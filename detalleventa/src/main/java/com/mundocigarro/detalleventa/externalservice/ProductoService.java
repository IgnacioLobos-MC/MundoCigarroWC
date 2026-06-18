package com.mundocigarro.detalleventa.externalservice;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mundocigarro.detalleventa.dto.ProductoDto;

@Service
public class ProductoService {

    private final WebClient webClient;

    public ProductoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ProductoDto obtenerProducto(Long idProducto){

        try {

            return webClient.get()
                    .uri("http://localhost:8082/api/v1/productos/" + idProducto)
                    .retrieve()
                    .bodyToMono(ProductoDto.class)
                    .block();

        } catch(Exception e){

            System.out.println(
                "Error al obtener producto desde el microservicio Producto: "
                + e.getMessage());

            return null;
        }
    }
}