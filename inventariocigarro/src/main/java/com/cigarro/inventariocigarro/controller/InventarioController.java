package com.cigarro.inventariocigarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cigarro.inventariocigarro.model.Inventario;
import com.cigarro.inventariocigarro.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/inventario")
@Tag(name = "Inventario", description = "Operaciones relacionadas con el inventario de cigarros")

public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    @Operation(summary = "Crear inventario", description = "Crea un nuevo registro de inventario para un cigarro")

    public Inventario crear(@RequestBody Inventario inventario) {

        return inventarioService.guardar(inventario);
    }

    @GetMapping
    @Operation(summary = "Listar inventario", description = "Obtiene una lista de todos los registros de inventario")
    
    public List<Inventario> listar() {

        return inventarioService.listar();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inventario", description = "Actualiza un registro de inventario existente")
    
    public Inventario actualizar(@PathVariable Long id,
                                 @RequestBody Inventario inventario) {

        return inventarioService.actualizar(id, inventario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inventario", description = "Elimina un registro de inventario existente")
    
    public String eliminar(@PathVariable Long id) {

        inventarioService.eliminar(id);

        return "Producto eliminado correctamente";
    }


}
