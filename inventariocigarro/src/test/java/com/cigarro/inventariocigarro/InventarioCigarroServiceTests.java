package com.cigarro.inventariocigarro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cigarro.inventariocigarro.model.Inventario;
import com.cigarro.inventariocigarro.repository.InventarioRepository;
import com.cigarro.inventariocigarro.service.InventarioService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InventarioCigarroServiceTests {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    @DisplayName("Debe listar inventario")
    void testListar() {

        Inventario inventario = new Inventario();

        when(inventarioRepository.findAll())
                .thenReturn(Arrays.asList(inventario));

        List<Inventario> resultado =
                inventarioService.listar();

        assertEquals(1, resultado.size());

        verify(inventarioRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe guardar inventario")
    void testGuardar() {

        Inventario inventario = new Inventario();

        when(inventarioRepository.save(inventario))
                .thenReturn(inventario);

        Inventario resultado =
                inventarioService.guardar(inventario);

        assertNotNull(resultado);

        verify(inventarioRepository, times(1))
                .save(inventario);
    }

    @Test
    @DisplayName("Debe actualizar inventario")
    void testActualizar() {

        Inventario existente = new Inventario();

        when(inventarioRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(inventarioRepository.save(any(Inventario.class)))
                .thenReturn(existente);

        Inventario resultado =
                inventarioService.actualizar(1L, existente);

        assertNotNull(resultado);

        verify(inventarioRepository, times(1))
                .findById(1L);

        verify(inventarioRepository, times(1))
                .save(any(Inventario.class));
    }

    @Test
    @DisplayName("Debe eliminar inventario")
    void testEliminar() {

        inventarioService.eliminar(1L);

        verify(inventarioRepository, times(1))
                .deleteById(1L);
    }
}