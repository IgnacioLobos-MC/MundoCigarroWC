package com.cigarro.gestioncigarro.externalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.cigarro.gestioncigarro.dto.ProveedorDTO;

@Service
public class ProveedorService {

    @Autowired
    private WebClient webClient;

    public ProveedorDTO obtenerProveedor(Long idProveedor){

        return webClient
                .get()
                .uri("http://localhost:8088/api/v1/proveedores/" + idProveedor)
                .retrieve()
                .bodyToMono(ProveedorDTO.class)
                .block();
    }
}