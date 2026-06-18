package com.mundocigarro.conductor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundocigarro.conductor.model.Conductor;
import com.mundocigarro.conductor.service.ConductorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/conductores")
@Tag(
    name = "Conductor Controller",
    description = "Endpoints para gestionar conductores"
)
public class ConductorController {

     @Autowired
    private ConductorService conductorService;

    @GetMapping
    @Operation(
        summary = "Listar Conductores",
        description = "Obtiene una lista de todos los conductores registrados"
    )
    public ResponseEntity<List<Conductor>> listar(){

        List<Conductor> conductores =
                conductorService.mostrarConductores();

        if(conductores.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(conductores);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Conductor",
        description = "Obtiene un conductor específico por su ID"
    )
    public ResponseEntity<Conductor> buscar(
            @PathVariable Long id){

        Conductor conductor =
                conductorService.obtenerConductorPorId(id);

        if(conductor == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(conductor);
    }

    @PostMapping
    @Operation(
        summary = "Crear Conductor",
        description = "Crea un nuevo conductor"
    )
    public ResponseEntity<Conductor> guardar(
            @RequestBody Conductor conductor){

        return ResponseEntity.ok(
                conductorService.crearConductor(conductor));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Modificar Conductor",
        description = "Actualiza la información de un conductor existente"
    )
    public ResponseEntity<Conductor> modificar(
            @PathVariable Long id,
            @RequestBody Conductor conductor){

        try {

            Conductor cond =
                    conductorService.obtenerConductorPorId(id);

            cond.setIdConductor(id);
            cond.setNombre(conductor.getNombre());
            cond.setApellido(conductor.getApellido());
            cond.setRut(conductor.getRut());
            cond.setTelefono(conductor.getTelefono());
            cond.setLicencia(conductor.getLicencia());
            cond.setEstado(conductor.getEstado());

            conductorService.crearConductor(cond);

            return ResponseEntity.ok(cond);

        } catch (Exception e){

            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar Conductor",
        description = "Elimina un conductor existente"
    )
    public ResponseEntity<String> eliminar(
            @PathVariable Long id){

        try {

            conductorService.eliminarConductor(id);

            return ResponseEntity.ok(
                    "Conductor eliminado correctamente");

        } catch (Exception e){

            return ResponseEntity.notFound().build();
        }
    }

}
