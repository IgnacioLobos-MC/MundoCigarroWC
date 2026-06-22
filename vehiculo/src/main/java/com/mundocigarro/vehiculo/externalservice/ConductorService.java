package com.mundocigarro.vehiculo.externalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mundocigarro.vehiculo.dto.ConductorDto;

@Service
public class ConductorService {

     @Autowired
    private WebClient webClient;

    public ConductorDto obtenerConductorPorId(Long idConductor) {

        return webClient
                .get()
                .uri("http://localhost:9093/api/v1/conductores/" + idConductor)
                .retrieve()
                .bodyToMono(ConductorDto.class)
                .block();
    }

}
