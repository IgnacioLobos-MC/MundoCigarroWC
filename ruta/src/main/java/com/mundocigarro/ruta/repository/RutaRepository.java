package com.mundocigarro.ruta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mundocigarro.ruta.model.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long>{

}
