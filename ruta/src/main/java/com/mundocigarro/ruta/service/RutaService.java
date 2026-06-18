package com.mundocigarro.ruta.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mundocigarro.ruta.model.Ruta;
import com.mundocigarro.ruta.repository.RutaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> mostrarRutas(){
        return rutaRepository.findAll();
    }

    public Ruta obtenerRutaPorId(Long id){
        return rutaRepository.findById(id).orElse(null);
    }

    public Ruta crearRuta(Ruta ruta){
        return rutaRepository.save(ruta);
    }

    public void eliminarRuta(Long id){
        rutaRepository.deleteById(id);
    }

}
