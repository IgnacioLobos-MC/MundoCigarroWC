package com.mundocigarro.conductor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mundocigarro.conductor.model.Conductor;
import com.mundocigarro.conductor.repository.ConductorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConductorService {

    @Autowired
    private ConductorRepository conductorRepository;

    public List<Conductor> mostrarConductores(){
        return conductorRepository.findAll();
    }

    public Conductor obtenerConductorPorId(Long id){
        return conductorRepository.findById(id).orElse(null);
    }

    public Conductor crearConductor(Conductor conductor){
        return conductorRepository.save(conductor);
    }

    public void eliminarConductor(Long id){
        conductorRepository.deleteById(id);
    }

}
