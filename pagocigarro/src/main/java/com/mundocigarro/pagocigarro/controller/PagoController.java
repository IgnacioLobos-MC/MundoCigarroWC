package com.mundocigarro.pagocigarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mundocigarro.pagocigarro.model.Pago;
import com.mundocigarro.pagocigarro.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos de cigarros")

public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    @Operation(summary = "Listar pagos", description = "Obtiene una lista de todos los registros de pagos")
    
    public List<Pago> listar() {
        return pagoService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pago", description = "Obtiene un registro de pago específico por su ID")
    public Pago buscar(@PathVariable Long id) {
        return pagoService.buscar(id);
    }

    @PostMapping
    @Operation(summary = "Crear pago", description = "Crea un nuevo registro de pago")
    public Pago guardar(@RequestBody Pago pago) {
        return pagoService.guardar(pago);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pago", description = "Actualiza un registro de pago existente")
    public Pago actualizar(@PathVariable Long id,
                           @RequestBody Pago pago) {

        return pagoService.actualizar(id, pago);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pago", description = "Elimina un registro de pago específico por su ID")
    public void eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
    }


}
