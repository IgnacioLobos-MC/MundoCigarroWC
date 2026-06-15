package com.mundocigarro.detalleventa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundocigarro.detalleventa.model.DetalleVenta;
import com.mundocigarro.detalleventa.service.DetalleVentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/detalleventa")
@Tag(name = "DetalleVenta", description = "Operaciones relacionadas con los detalles de venta")

public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    @Operation(summary = "Listar detalles de venta", description = "Obtiene una lista de todos los detalles de venta")

    public ResponseEntity<List<DetalleVenta>> listar(){

        List<DetalleVenta> lista = detalleVentaService.listar();

        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar detalle de venta", description = "Obtiene un detalle de venta por su ID")
    
    public ResponseEntity<DetalleVenta> buscar(@PathVariable Long id){

        try{
            DetalleVenta detalle = detalleVentaService.buscar(id);
            return ResponseEntity.ok(detalle);

        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/venta/{idVenta}")
    @Operation(summary = "Buscar detalles de venta por ID de venta", description = "Obtiene una lista de detalles de venta por su ID de venta")

    public ResponseEntity<List<DetalleVenta>> buscarPorVenta(@PathVariable Long idVenta){

        List<DetalleVenta> lista = detalleVentaService.buscarPorVenta(idVenta);

        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Operation(summary = "Guardar detalle de venta", description = "Crea un nuevo detalle de venta")
    
    public ResponseEntity<DetalleVenta> guardar(@RequestBody DetalleVenta detalleVenta){

        DetalleVenta nuevo = detalleVentaService.guardar(detalleVenta);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar detalle de venta", description = "Elimina un detalle de venta por su ID")
    
    public ResponseEntity<Void> eliminar(@PathVariable Long id){

        detalleVentaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}