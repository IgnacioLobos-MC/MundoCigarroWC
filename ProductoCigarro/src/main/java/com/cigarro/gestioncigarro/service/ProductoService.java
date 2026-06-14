package com.cigarro.gestioncigarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cigarro.gestioncigarro.dto.ProveedorDTO;
import com.cigarro.gestioncigarro.externalservice.ProveedorService;
import com.cigarro.gestioncigarro.model.Producto;
import com.cigarro.gestioncigarro.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorService proveedorService;

    public List<Producto> mostrarProducto() {
        return productoRepository.findAll();
    }

    public Producto buscaProductoId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto creaProducto(Producto unProducto) {
        ProveedorDTO proveedor;
        
        try {
            proveedor = proveedorService.obtenerProveedor(unProducto.getIdProveedor());

        } catch (Exception e) {
            throw new RuntimeException("El proveedor indicado no existe");
        }

        if (proveedor == null) {
            throw new RuntimeException("El proveedor indicado no existe");
        }
        return productoRepository.save(unProducto);
    }
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}