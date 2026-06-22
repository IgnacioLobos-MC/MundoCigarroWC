package com.mundocigarro.ruta;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mundocigarro.ruta.model.Ruta;
import com.mundocigarro.ruta.repository.RutaRepository;
import com.mundocigarro.ruta.service.RutaService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RutaServiceTests {

    @Mock
    private RutaRepository rutaRepository;

    @InjectMocks
    private RutaService rutaService;

    @Test
    @DisplayName("Debe listar todas las rutas")
    void testMostrarRutas() {

        Ruta ruta = new Ruta();
        ruta.setIdRuta(1L);
        ruta.setNombreRuta("Ruta Santiago");

        when(rutaRepository.findAll())
                .thenReturn(Arrays.asList(ruta));

        List<Ruta> resultado =
                rutaService.mostrarRutas();

        assertEquals(1, resultado.size());

        verify(rutaRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar ruta por ID")
    void testBuscarRutaPorId() {

        Ruta ruta = new Ruta();
        ruta.setIdRuta(1L);
        ruta.setNombreRuta("Ruta Santiago");

        when(rutaRepository.findById(1L))
                .thenReturn(Optional.of(ruta));

        Ruta resultado =
                rutaService.obtenerRutaPorId(1L);

        assertNotNull(resultado);
        assertEquals("Ruta Santiago",
                resultado.getNombreRuta());

        verify(rutaRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe crear ruta")
    void testCrearRuta() {

        Ruta ruta = new Ruta();
        ruta.setIdRuta(1L);
        ruta.setNombreRuta("Ruta Santiago");

        when(rutaRepository.save(ruta))
                .thenReturn(ruta);

        Ruta resultado =
                rutaService.crearRuta(ruta);

        assertNotNull(resultado);

        verify(rutaRepository, times(1))
                .save(ruta);
    }

    @Test
    @DisplayName("Debe eliminar ruta")
    void testEliminarRuta() {

        rutaService.eliminarRuta(1L);

        verify(rutaRepository, times(1))
                .deleteById(1L);
    }

}
