package com.mundocigarro.pagocigarro;

import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("Debe eliminar pago")
    void testEliminarPago() {

        pagoService.eliminar(1L);

        verify(pagoRepository, times(1))
                .deleteById(1L);
    }
}