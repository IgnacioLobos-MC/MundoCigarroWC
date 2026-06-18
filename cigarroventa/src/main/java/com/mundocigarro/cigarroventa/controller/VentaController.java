package com.mundocigarro.cigarroventa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundocigarro.cigarroventa.model.Venta;
import com.mundocigarro.cigarroventa.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas",
    description = "Endpoints para gestionar las ventas de Mundo Cigarro"
)

public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    @Operation(summary = "Listar Ventas",
        description = "Obtiene una lista de todas las ventas registradas en Mundo Cigarro")

    public ResponseEntity<List<Venta>> listar(){

        List<Venta> ventas = ventaService.mostrarVentas();

        if(ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Venta por su ID",
        description = "Obtiene los detalles de una venta específica utilizando su ID")

    public ResponseEntity<Venta> buscar(@PathVariable Long id){

        Venta venta = ventaService.obtenerVentaPorId(id);

        if(venta == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(venta);
    }

    @PostMapping
    @Operation(summary = "Registrar Nueva Venta",
        description = "Permite registrar una nueva venta en Mundo Cigarro. Se requiere el ID del cliente, la fecha de la venta y el total de la venta")

    public ResponseEntity<?> guardar(@RequestBody Venta venta){

        Venta nuevaVenta = ventaService.crearVenta(venta);

        if(nuevaVenta == null){
            return ResponseEntity.badRequest()
                    .body("El cliente no existe");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevaVenta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar una Venta existente",
        description = "Actualiza los detalles de una venta existente en la base de datos de Mundo Cigarro. Se requiere el ID de la venta a modificar, el ID del cliente, la fecha de la venta y el total de la venta")
        
    public ResponseEntity<Venta> modificar(@PathVariable Long id,
                                           @RequestBody Venta venta){

        try {

            Venta ven = ventaService.obtenerVentaPorId(id);

            ven.setIdVenta(id);
            ven.setIdCliente(venta.getIdCliente());
            ven.setFechaVenta(venta.getFechaVenta());
            ven.setTotalVenta(venta.getTotalVenta());

            ventaService.crearVenta(ven);

            return ResponseEntity.ok(ven);

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una Venta",
        description = "Elimina una venta específica de la base de datos de Mundo Cigarro. Se requiere el ID de la venta a eliminar")
        
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        try {

            ventaService.eliminarVenta(id);

            return ResponseEntity.ok(
                    "Venta eliminada correctamente");

        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }
}