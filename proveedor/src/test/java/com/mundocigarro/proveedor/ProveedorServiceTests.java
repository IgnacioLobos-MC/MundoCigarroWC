package com.mundocigarro.proveedor;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.proveedor.model.Proveedor;
import com.mundocigarro.proveedor.repository.ProveedorRepository;
import com.mundocigarro.proveedor.service.ProveedorService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTests {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    @DisplayName("Debe listar todos los proveedores")
    void testMostrarProveedores() {

        Proveedor proveedor = new Proveedor();

        when(proveedorRepository.findAll())
                .thenReturn(Arrays.asList(proveedor));

        List<Proveedor> resultado =
                proveedorService.mostrarProveedores();

        assertEquals(1, resultado.size());

        verify(proveedorRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar proveedor por ID")
    void testObtenerProveedorPorId() {

        Proveedor proveedor = new Proveedor();

        when(proveedorRepository.findById(1L))
                .thenReturn(Optional.of(proveedor));

        Proveedor resultado =
                proveedorService.obtenerProveedorPorId(1L);

        assertNotNull(resultado);

        verify(proveedorRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe crear proveedor")
    void testCrearProveedor() {

        Proveedor proveedor = new Proveedor();

        when(proveedorRepository.save(proveedor))
                .thenReturn(proveedor);

        Proveedor resultado =
                proveedorService.crearProveedor(proveedor);

        assertNotNull(resultado);

        verify(proveedorRepository, times(1))
                .save(proveedor);
    }

    @Test
    @DisplayName("Debe eliminar proveedor")
    void testEliminarProveedor() {

        proveedorService.eliminarProveedor(1L);

        verify(proveedorRepository, times(1))
                .deleteById(1L);
    }
}