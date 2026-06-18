package com.mundocigarro.cigarroventa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.cigarroventa.dto.ClienteDto;
import com.mundocigarro.cigarroventa.externalservice.ClienteService;
import com.mundocigarro.cigarroventa.model.Venta;
import com.mundocigarro.cigarroventa.repository.VentaRepository;
import com.mundocigarro.cigarroventa.service.VentaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CigarroVentaServiceTests {

    @Mock
    private VentaRepository ventaRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private VentaService ventaService;

    @Test
    @DisplayName("Debe listar ventas")
    void testMostrarVentas() {

        Venta venta = new Venta();

        when(ventaRepository.findAll())
                .thenReturn(Arrays.asList(venta));

        List<Venta> resultado =
                ventaService.mostrarVentas();

        assertEquals(1, resultado.size());

        verify(ventaRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe obtener venta por ID")
    void testObtenerVentaPorId() {

        Venta venta = new Venta();

        when(ventaRepository.findById(1L))
                .thenReturn(Optional.of(venta));

        Venta resultado =
                ventaService.obtenerVentaPorId(1L);

        assertNotNull(resultado);

        verify(ventaRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe crear venta cuando el cliente existe")
    void testCrearVenta() {

        Venta venta = new Venta();
        venta.setIdCliente(1L);

        ClienteDto cliente = new ClienteDto();

        when(clienteService.obternerCliente(1L))
                .thenReturn(cliente);

        when(ventaRepository.save(venta))
                .thenReturn(venta);

        Venta resultado =
                ventaService.crearVenta(venta);

        assertNotNull(resultado);

        verify(ventaRepository, times(1))
                .save(venta);
    }

    @Test
    @DisplayName("No debe crear venta cuando el cliente no existe")
    void testCrearVentaClienteInexistente() {

        Venta venta = new Venta();
        venta.setIdCliente(1L);

        when(clienteService.obternerCliente(1L))
                .thenReturn(null);

        Venta resultado =
                ventaService.crearVenta(venta);

        assertNull(resultado);

        verify(ventaRepository, never())
                .save(any(Venta.class));
    }

    @Test
    @DisplayName("Debe eliminar venta")
    void testEliminarVenta() {

        ventaService.eliminarVenta(1L);

        verify(ventaRepository, times(1))
                .deleteById(1L);
    }
}