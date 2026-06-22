package com.mundocigarro.vehiculo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.vehiculo.model.Vehiculo;
import com.mundocigarro.vehiculo.repository.VehiculoRepository;
import com.mundocigarro.vehiculo.service.VehiculoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VehiculoServiceTest {

     @Mock
    private VehiculoRepository vehiculoRepository;

    @InjectMocks
    private VehiculoService vehiculoService;

    @Test
    @DisplayName("Debe listar vehículos")
    void testMostrarVehiculos() {

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo(1L);
        vehiculo.setPatente("AA1111");

        when(vehiculoRepository.findAll())
                .thenReturn(Arrays.asList(vehiculo));

        List<Vehiculo> resultado =
                vehiculoService.mostrarVehiculos();

        assertEquals(1, resultado.size());

        verify(vehiculoRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar vehículo por ID")
    void testBuscarVehiculoPorId() {

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo(1L);
        vehiculo.setPatente("AA1111");

        when(vehiculoRepository.findById(1L))
                .thenReturn(Optional.of(vehiculo));

        Vehiculo resultado =
                vehiculoService.obtenerVehiculoPorId(1L);

        assertNotNull(resultado);

        verify(vehiculoRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe crear vehículo")
    void testCrearVehiculo() {

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo(1L);
        vehiculo.setPatente("AA1111");

        when(vehiculoRepository.save(vehiculo))
                .thenReturn(vehiculo);

        Vehiculo resultado =
                vehiculoService.crearVehiculo(vehiculo);

        assertNotNull(resultado);

        verify(vehiculoRepository, times(1))
                .save(vehiculo);
    }

    @Test
    @DisplayName("Debe eliminar vehículo")
    void testEliminarVehiculo() {

        vehiculoService.eliminarVehiculo(1L);

        verify(vehiculoRepository, times(1))
                .deleteById(1L);
    }

}
