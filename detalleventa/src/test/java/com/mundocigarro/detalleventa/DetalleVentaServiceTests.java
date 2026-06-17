package com.mundocigarro.detalleventa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.detalleventa.dto.ProductoDto;
import com.mundocigarro.detalleventa.dto.VentaDto;
import com.mundocigarro.detalleventa.externalservice.ProductoService;
import com.mundocigarro.detalleventa.externalservice.VentaService;
import com.mundocigarro.detalleventa.model.DetalleVenta;
import com.mundocigarro.detalleventa.repository.DetalleVentaRepository;
import com.mundocigarro.detalleventa.service.DetalleVentaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DetalleVentaServiceTests {

    @Mock
    private DetalleVentaRepository detalleVentaRepository;

    @Mock
    private ProductoService productoService;

    @Mock
    private VentaService ventaService;

    @InjectMocks
    private DetalleVentaService detalleVentaService;

    @Test
    @DisplayName("Debe listar detalles de venta")
    void testListar() {

        DetalleVenta detalle = new DetalleVenta();

        when(detalleVentaRepository.findAll())
                .thenReturn(Arrays.asList(detalle));

        List<DetalleVenta> resultado =
                detalleVentaService.listar();

        assertEquals(1, resultado.size());

        verify(detalleVentaRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar detalle por ID")
    void testBuscar() {

        DetalleVenta detalle = new DetalleVenta();

        when(detalleVentaRepository.findById(1L))
                .thenReturn(Optional.of(detalle));

        DetalleVenta resultado =
                detalleVentaService.buscar(1L);

        assertNotNull(resultado);

        verify(detalleVentaRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe buscar detalles por venta")
    void testBuscarPorVenta() {

        DetalleVenta detalle = new DetalleVenta();

        when(detalleVentaRepository.findByIdVenta(1L))
                .thenReturn(Arrays.asList(detalle));

        List<DetalleVenta> resultado =
                detalleVentaService.buscarPorVenta(1L);

        assertEquals(1, resultado.size());

        verify(detalleVentaRepository, times(1))
                .findByIdVenta(1L);
    }

    @Test
    @DisplayName("Debe guardar detalle de venta calculando subtotal")
    void testGuardar() {

        DetalleVenta detalle = new DetalleVenta();

        detalle.setIdProducto(1L);
        detalle.setIdVenta(1L);
        detalle.setCantidad(2);

        ProductoDto producto = new ProductoDto();
        producto.setPrecio(1000.0);

        VentaDto venta = new VentaDto();

        when(productoService.obtenerProducto(1L))
                .thenReturn(producto);

        when(ventaService.obtenerVenta(1L))
                .thenReturn(venta);

        when(detalleVentaRepository.save(any(DetalleVenta.class)))
                .thenReturn(detalle);

        DetalleVenta resultado =
                detalleVentaService.guardar(detalle);

        assertNotNull(resultado);

        verify(detalleVentaRepository, times(1))
                .save(any(DetalleVenta.class));
    }
    
    @Test
@DisplayName("No debe guardar detalle cuando la venta no existe")
void testGuardarDetalleVentaInexistente() {

    DetalleVenta detalle = new DetalleVenta();

    detalle.setIdProducto(1L);
    detalle.setIdVenta(1L);

    ProductoDto producto = new ProductoDto();
    producto.setPrecio(1000.0);

    when(productoService.obtenerProducto(1L))
            .thenReturn(producto);

    when(ventaService.obtenerVenta(1L))
            .thenReturn(null);

    DetalleVenta resultado =
            detalleVentaService.guardar(detalle);

    assertNull(resultado);

    verify(detalleVentaRepository, never())
            .save(any(DetalleVenta.class));
}

    @Test
    @DisplayName("Debe eliminar detalle de venta")
    void testEliminar() {

        detalleVentaService.eliminar(1L);

        verify(detalleVentaRepository, times(1))
                .deleteById(1L);
    }
}