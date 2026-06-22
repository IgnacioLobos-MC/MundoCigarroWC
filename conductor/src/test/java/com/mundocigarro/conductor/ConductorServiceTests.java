package com.mundocigarro.conductor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.conductor.model.Conductor;
import com.mundocigarro.conductor.repository.ConductorRepository;
import com.mundocigarro.conductor.service.ConductorService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class ConductorServiceTests {

     @Mock
    private ConductorRepository conductorRepository;

    @InjectMocks
    private ConductorService conductorService;

    @Test
    @DisplayName("Debe listar conductores")
    void testMostrarConductores() {

        Conductor conductor = new Conductor();
        conductor.setIdConductor(1L);
        conductor.setNombre("Juan");

        when(conductorRepository.findAll())
                .thenReturn(Arrays.asList(conductor));

        List<Conductor> resultado =
                conductorService.mostrarConductores();

        assertEquals(1, resultado.size());

        verify(conductorRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar conductor por ID")
    void testBuscarConductorPorId() {

        Conductor conductor = new Conductor();
        conductor.setIdConductor(1L);
        conductor.setNombre("Juan");

        when(conductorRepository.findById(1L))
                .thenReturn(Optional.of(conductor));

        Conductor resultado =
                conductorService.obtenerConductorPorId(1L);

        assertNotNull(resultado);
        assertEquals("Juan",
                resultado.getNombre());

        verify(conductorRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe crear conductor")
    void testCrearConductor() {

        Conductor conductor = new Conductor();
        conductor.setIdConductor(1L);
        conductor.setNombre("Juan");

        when(conductorRepository.save(conductor))
                .thenReturn(conductor);

        Conductor resultado =
                conductorService.crearConductor(conductor);

        assertNotNull(resultado);

        verify(conductorRepository, times(1))
                .save(conductor);
    }

    @Test
    @DisplayName("Debe eliminar conductor")
    void testEliminarConductor() {

        conductorService.eliminarConductor(1L);

        verify(conductorRepository, times(1))
                .deleteById(1L);
    }

}
