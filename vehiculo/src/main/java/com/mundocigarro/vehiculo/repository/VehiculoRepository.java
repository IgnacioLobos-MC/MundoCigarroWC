package com.mundocigarro.vehiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mundocigarro.vehiculo.model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
