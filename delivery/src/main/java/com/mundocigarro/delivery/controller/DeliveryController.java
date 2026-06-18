package com.mundocigarro.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mundocigarro.delivery.model.Delivery;
import com.mundocigarro.delivery.service.DeliveryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/delivery")
@Tag(name = "Delivery", 
description = "Operaciones relacionadas con las entregas")

public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping
    @Operation(summary = "Listar entregas", description = "Obtiene una lista de todas las entregas")
    
    public ResponseEntity<List<Delivery>> listar() {

        List<Delivery> lista = deliveryService.listar();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar entrega", description = "Obtiene los detalles de una entrega específica por su ID")

    public ResponseEntity<Delivery> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.buscar(id));
    }

    @PostMapping
    @Operation(summary = "Crear entrega", description = "Crea una nueva entrega")
    
    public ResponseEntity<Delivery> guardar(@RequestBody Delivery delivery) {

        Delivery nuevo = deliveryService.guardar(delivery);

        return ResponseEntity.ok(nuevo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entrega", description = "Elimina una entrega específica por su ID")
    
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        deliveryService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}