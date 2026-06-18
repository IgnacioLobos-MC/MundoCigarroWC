package com.cigarro.clientecigarro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cigarro.clientecigarro.model.Cliente;
import com.cigarro.clientecigarro.repository.ClienteRepository;
import com.cigarro.clientecigarro.service.ClienteService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("Debe listar clientes")
    void testMostrarCliente() {

        Cliente cliente = new Cliente();

        when(clienteRepository.findAll())
                .thenReturn(Arrays.asList(cliente));

        List<Cliente> resultado =
                clienteService.mostrarCliente();

        assertEquals(1, resultado.size());

        verify(clienteRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar cliente por ID")
    void testBuscarClientePorId() {

        Cliente cliente = new Cliente();

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        Cliente resultado =
                clienteService.buscaClienteId(1L);

        assertNotNull(resultado);

        verify(clienteRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe crear cliente")
    void testCrearCliente() {

        Cliente cliente = new Cliente();

        when(clienteRepository.save(cliente))
                .thenReturn(cliente);

        Cliente resultado =
                clienteService.creaCliente(cliente);

        assertNotNull(resultado);

        verify(clienteRepository, times(1))
                .save(cliente);
    }

    @Test
    @DisplayName("Debe eliminar cliente")
    void testEliminarCliente() {

        clienteService.eliminarCliente(1L);

        verify(clienteRepository, times(1))
                .deleteById(1L);
    }
}