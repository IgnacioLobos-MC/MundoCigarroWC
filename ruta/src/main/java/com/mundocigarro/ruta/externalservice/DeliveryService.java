package com.mundocigarro.ruta.externalservice;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mundocigarro.ruta.dto.DeliveryDto;

@Service
public class DeliveryService {


    private final WebClient webClient;

    public DeliveryService(WebClient webClient) {
        this.webClient = webClient;
    }

    public DeliveryDto obtenerDelivery(Long idDelivery){

        try {

            return webClient.get()
                    .uri("http://localhost:8085/api/v1/delivery/" + idDelivery)
                    .retrieve()
                    .bodyToMono(DeliveryDto.class)
                    .block();

        } catch(Exception e){
            return null;
        }
    }

}
