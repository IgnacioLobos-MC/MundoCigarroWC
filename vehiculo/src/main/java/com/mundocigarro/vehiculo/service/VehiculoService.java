package com.mundocigarro.vehiculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mundocigarro.vehiculo.model.Vehiculo;
import com.mundocigarro.vehiculo.repository.VehiculoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> mostrarVehiculos(){
        return vehiculoRepository.findAll();
    }

    public Vehiculo obtenerVehiculoPorId(Long id){
        return vehiculoRepository.findById(id).orElse(null);
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo){
        return vehiculoRepository.save(vehiculo);
    }

    public void eliminarVehiculo(Long id){
        vehiculoRepository.deleteById(id);
    }

}
