package com.mundocigarro.delivery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.delivery.dto.VentaDto;
import com.mundocigarro.delivery.externalservice.VentaService;
import com.mundocigarro.delivery.model.Delivery;
import com.mundocigarro.delivery.repository.DeliveryRepository;
import com.mundocigarro.delivery.service.DeliveryService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTests {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private VentaService ventaService;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    @DisplayName("Debe listar deliveries")
    void testListar() {

        Delivery delivery = new Delivery();

        when(deliveryRepository.findAll())
                .thenReturn(Arrays.asList(delivery));

        List<Delivery> resultado =
                deliveryService.listar();

        assertEquals(1, resultado.size());

        verify(deliveryRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar delivery por ID")
    void testBuscar() {

        Delivery delivery = new Delivery();

        when(deliveryRepository.findById(1L))
                .thenReturn(Optional.of(delivery));

        Delivery resultado =
                deliveryService.buscar(1L);

        assertNotNull(resultado);

        verify(deliveryRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe guardar delivery cuando existe la venta")
    void testGuardar() {

        Delivery delivery = new Delivery();
        delivery.setIdVenta(1L);

        VentaDto venta = new VentaDto();

        when(ventaService.obtenerVenta(1L))
                .thenReturn(venta);

        when(deliveryRepository.save(delivery))
                .thenReturn(delivery);

        Delivery resultado =
                deliveryService.guardar(delivery);

        assertNotNull(resultado);

        verify(deliveryRepository, times(1))
                .save(delivery);
    }

    @Test
    @DisplayName("No debe guardar delivery cuando la venta no existe")
    void testGuardarVentaInexistente() {

        Delivery delivery = new Delivery();
        delivery.setIdVenta(1L);

        when(ventaService.obtenerVenta(1L))
                .thenReturn(null);

        Delivery resultado =
                deliveryService.guardar(delivery);

        assertNull(resultado);

        verify(deliveryRepository, never())
                .save(any(Delivery.class));
    }

    @Test
    @DisplayName("Debe eliminar delivery")
    void testEliminar() {

        deliveryService.eliminar(1L);

        verify(deliveryRepository, times(1))
                .deleteById(1L);
    }
}