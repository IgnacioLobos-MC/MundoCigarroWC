package com.mundocigarro.vehiculo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundocigarro.vehiculo.model.Vehiculo;
import com.mundocigarro.vehiculo.service.VehiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/vehiculos")
@Tag(
    name = "Vehiculo Controller",
    description = "Endpoints para gestionar vehículos"
)
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    @Operation(
        summary = "Listar Vehículos",
        description = "Obtiene una lista de todos los vehículos registrados"
    )
    public ResponseEntity<List<Vehiculo>> listar(){

        List<Vehiculo> vehiculos =
                vehiculoService.mostrarVehiculos();

        if(vehiculos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Vehículo",
        description = "Obtiene un vehículo específico por su ID"
    )
    public ResponseEntity<Vehiculo> buscar(
            @PathVariable Long id){

        Vehiculo vehiculo =
                vehiculoService.obtenerVehiculoPorId(id);

        if(vehiculo == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(vehiculo);
    }

    @PostMapping
    @Operation(
        summary = "Crear Vehículo",
        description = "Crea un nuevo vehículo"
    )
    public ResponseEntity<Vehiculo> guardar(
            @RequestBody Vehiculo vehiculo){

        return ResponseEntity.ok(
                vehiculoService.crearVehiculo(vehiculo));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Modificar Vehículo",
        description = "Actualiza la información de un vehículo existente"
    )
    public ResponseEntity<Vehiculo> modificar(
            @PathVariable Long id,
            @RequestBody Vehiculo vehiculo){

        try {

            Vehiculo veh =
                    vehiculoService.obtenerVehiculoPorId(id);

            veh.setIdVehiculo(id);
            veh.setPatente(vehiculo.getPatente());
            veh.setMarca(vehiculo.getMarca());
            veh.setModelo(vehiculo.getModelo());
            veh.setCapacidadCarga(vehiculo.getCapacidadCarga());
            veh.setEstado(vehiculo.getEstado());

            vehiculoService.crearVehiculo(veh);

            return ResponseEntity.ok(veh);

        } catch (Exception e){

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar Vehículo",
        description = "Elimina un vehículo existente"
    )
    public ResponseEntity<String> eliminar(
            @PathVariable Long id){

        try {

            vehiculoService.eliminarVehiculo(id);

            return ResponseEntity.ok(
                    "Vehículo eliminado correctamente");

        } catch (Exception e){

            return ResponseEntity.notFound().build();
        }
    }
}