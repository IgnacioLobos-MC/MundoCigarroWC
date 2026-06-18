package com.mundocigarro.proveedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundocigarro.proveedor.model.Proveedor;
import com.mundocigarro.proveedor.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedor Controller", description = "Endpoints para gestionar proveedores")

public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Listar Proveedores", description = "Obtiene una lista de todos los proveedores registrados")
    
    public ResponseEntity<List<Proveedor>> listar(){
        List<Proveedor> proveedores =
                proveedorService.mostrarProveedores();
        if(proveedores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Proveedor", description = "Obtiene un proveedor específico por su ID")
    
    public ResponseEntity<Proveedor> buscar(@PathVariable Long id){

        Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
        if(proveedor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    @Operation(summary = "Crear Proveedor", description = "Crea un nuevo proveedor")
    
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor proveedor){

        return ResponseEntity.ok( proveedorService.crearProveedor(proveedor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar Proveedor", description = "Actualiza la información de un proveedor existente")
    
    public ResponseEntity<Proveedor> modificar(@PathVariable Long id, @RequestBody Proveedor proveedor){

        try {

            Proveedor prov = proveedorService.obtenerProveedorPorId(id);

            prov.setIdProveedor(id);
            prov.setNombreProveedor(proveedor.getNombreProveedor());

            prov.setRutEmpresa(proveedor.getRutEmpresa());
            prov.setDireccion(proveedor.getDireccion());
            prov.setTelefono(proveedor.getTelefono());
            prov.setEmail(proveedor.getEmail());
            proveedorService.crearProveedor(prov);

            return ResponseEntity.ok(prov);

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Proveedor", description = "Elimina un proveedor existente")
    
    public ResponseEntity<String> eliminar(
            @PathVariable Long id){

        try {

            proveedorService.eliminarProveedor(id);

            return ResponseEntity.ok(
                    "Proveedor eliminado correctamente");

        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}