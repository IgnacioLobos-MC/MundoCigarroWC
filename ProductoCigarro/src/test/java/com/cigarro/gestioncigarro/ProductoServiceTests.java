package com.cigarro.gestioncigarro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cigarro.gestioncigarro.dto.ProveedorDTO;
import com.cigarro.gestioncigarro.externalservice.ProveedorService;
import com.cigarro.gestioncigarro.model.Producto;
import com.cigarro.gestioncigarro.repository.ProductoRepository;
import com.cigarro.gestioncigarro.service.ProductoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTests {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProveedorService proveedorService;

    @InjectMocks
    private ProductoService productoService;

    @Test
    @DisplayName("Debe listar todos los productos")
    void testMostrarProductos() {

        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setNombre("Cohiba");

        when(productoRepository.findAll())
                .thenReturn(Arrays.asList(producto));

        List<Producto> resultado =
                productoService.mostrarProducto();

        assertEquals(1, resultado.size());

        verify(productoRepository, times(1))
                .findAll();
    }

    @Test
    @DisplayName("Debe buscar producto por ID")
    void testBuscarProductoPorId() {

        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setNombre("Cohiba");

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        Producto resultado =
                productoService.buscaProductoId(1L);

        assertNotNull(resultado);
        assertEquals("Cohiba",
                resultado.getNombre());

        verify(productoRepository, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("Debe crear producto cuando proveedor existe")
    void testCrearProducto() {

        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setNombre("Cohiba");
        producto.setIdProveedor(1L);

        ProveedorDTO proveedor =
                new ProveedorDTO();

        when(proveedorService.obtenerProveedor(1L))
                .thenReturn(proveedor);

        when(productoRepository.save(producto))
                .thenReturn(producto);

        Producto resultado =
                productoService.creaProducto(producto);

        assertNotNull(resultado);
        assertEquals("Cohiba",
                resultado.getNombre());

        verify(productoRepository, times(1))
                .save(producto);
    }

    @Test
    @DisplayName("Debe eliminar producto correctamente")
    void testEliminarProducto() {

        productoService.eliminarProducto(1L);

        verify(productoRepository, times(1))
                .deleteById(1L);
    }
}