package com.mundocigarro.proveedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mundocigarro.proveedor.model.Proveedor;
import com.mundocigarro.proveedor.repository.ProveedorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> mostrarProveedores(){
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerProveedorPorId(Long id){
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor crearProveedor(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

    public void eliminarProveedor(Long id){
        proveedorRepository.deleteById(id);
    }
}