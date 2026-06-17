package com.mundocigarro.pagocigarro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.pagocigarro.dto.VentaDto;
import com.mundocigarro.pagocigarro.externalservice.VentaService;
import com.mundocigarro.pagocigarro.model.Pago;
import com.mundocigarro.pagocigarro.repository.PagoRepository;
import com.mundocigarro.pagocigarro.service.PagoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTests {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private VentaService ventaService;

    @InjectMocks
    private PagoService pagoService;

    @Test
    @DisplayName("Debe listar pagos")
    void testListarPagos() {

        Pago pago = new Pago();

        when(pagoRepository.findAll())
                .thenReturn(Arrays.asList(pago));

        List<Pago> resultado = pagoService.listar();

        assertEquals(1, resultado.size());

        verify(pagoRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar pago por ID")
    void testBuscarPago() {

        Pago pago = new Pago();

        when(pagoRepository.findById(1L))
                .thenReturn(Optional.of(pago));

        Pago resultado = pagoService.buscar(1L);

        assertNotNull(resultado);

        verify(pagoRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe guardar pago cuando existe la venta")
    void testGuardarPago() {

        Pago pago = new Pago();
        pago.setIdVenta(1L);

        VentaDto venta = new VentaDto();

        when(ventaService.obtenerVenta(1L))
                .thenReturn(venta);

        when(pagoRepository.save(pago))
                .thenReturn(pago);

        Pago resultado = pagoService.guardar(pago);

        assertNotNull(resultado);

        verify(pagoRepository, times(1))
                .save(pago);
    }

    @Test
        @DisplayName("No debe guardar pago cuando la venta no existe")
        void testGuardarPagoVentaInexistente() {

        Pago pago = new Pago();
        pago.setIdVenta(1L);

        when(ventaService.obtenerVenta(1L))
                .thenReturn(null);

        Pago resultado = pagoService.guardar(pago);

        assertNull(resultado);

        verify(pagoRepository, never())
                .save(any(Pago.class));
        }

        @Test
        @DisplayName("Debe actualizar un pago existente")
        void testActualizarPago() {

        Pago pagoExistente = new Pago();

        pagoExistente.setIdPago(1L);
        pagoExistente.setMetodoPago("Efectivo");
        pagoExistente.setMonto(10000.0);
        pagoExistente.setEstadoPago("Pendiente");

        Pago pagoActualizado = new Pago();

        pagoActualizado.setMetodoPago("Tarjeta");
        pagoActualizado.setMonto(15000.0);
        pagoActualizado.setEstadoPago("Pagado");

        when(pagoRepository.findById(1L))
                .thenReturn(Optional.of(pagoExistente));

        when(pagoRepository.save(any(Pago.class)))
                .thenReturn(pagoExistente);

        Pago resultado =
                pagoService.actualizar(1L, pagoActualizado);

        assertNotNull(resultado);

        assertEquals("Tarjeta",
                resultado.getMetodoPago());

        assertEquals(15000.0,
                resultado.getMonto());

        assertEquals("Pagado",
                resultado.getEstadoPago());

        verify(pagoRepository)
                .save(any(Pago.class));
        }

    @Test
    @DisplayName("Debe eliminar pago")
    void testEliminarPago() {

        pagoService.eliminar(1L);

        verify(pagoRepository, times(1))
                .deleteById(1L);
    }
}