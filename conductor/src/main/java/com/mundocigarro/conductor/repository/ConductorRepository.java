package com.mundocigarro.conductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mundocigarro.conductor.model.Conductor;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {

}
