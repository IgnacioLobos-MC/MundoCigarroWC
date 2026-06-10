package com.mundocigarro.cigarroventa.externalservice;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mundocigarro.cigarroventa.dto.ClienteDto;

@Service
public class ClienteService {

    private final WebClient webClient;

    public ClienteService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ClienteDto obternerCliente(Long idCliente){

        try {

            return webClient.get()
                    .uri("http://localhost:8081/api/v1/clientes/" + idCliente)
                    .retrieve()
                    .bodyToMono(ClienteDto.class)
                    .block();

        } catch(Exception e){
            return null;
        }
    }
}