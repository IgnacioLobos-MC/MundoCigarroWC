package com.mundocigarro.ruta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mundocigarro.ruta.model.Ruta;
import com.mundocigarro.ruta.service.RutaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/rutas")
@Tag(name = "Ruta Controller", description = "Endpoints para gestionar rutas")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @GetMapping
    @Operation(summary = "Listar Rutas", description = "Obtiene una lista de todas las rutas registradas")
    public ResponseEntity<List<Ruta>> listar() {

        List<Ruta> rutas = rutaService.mostrarRutas();

        if (rutas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(rutas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Ruta", description = "Obtiene una ruta específica por su ID")
    public ResponseEntity<Ruta> buscar(@PathVariable Long id) {

        Ruta ruta = rutaService.obtenerRutaPorId(id);

        if (ruta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ruta);
    }

    @PostMapping
    @Operation(summary = "Crear Ruta", description = "Crea una nueva ruta")
    public ResponseEntity<Ruta> guardar(@RequestBody Ruta ruta) {

        return ResponseEntity.ok(rutaService.crearRuta(ruta));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar Ruta", description = "Actualiza la información de una ruta existente")
    public ResponseEntity<Ruta> modificar(@PathVariable Long id, @RequestBody Ruta ruta) {

        try {

            Ruta rutaExistente = rutaService.obtenerRutaPorId(id);

            rutaExistente.setIdRuta(id);
            rutaExistente.setNombreRuta(ruta.getNombreRuta());
            rutaExistente.setOrigen(ruta.getOrigen());
            rutaExistente.setDestino(ruta.getDestino());
            rutaExistente.setDistanciaKm(ruta.getDistanciaKm());
            rutaExistente.setTiempoEstimado(ruta.getTiempoEstimado());
            rutaExistente.setEstado(ruta.getEstado());
            rutaExistente.setIdDelivery(ruta.getIdDelivery());

            rutaService.crearRuta(rutaExistente);

            return ResponseEntity.ok(rutaExistente);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Ruta", description = "Elimina una ruta existente")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        try {

            rutaService.eliminarRuta(id);

            return ResponseEntity.ok("Ruta eliminada correctamente");

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
