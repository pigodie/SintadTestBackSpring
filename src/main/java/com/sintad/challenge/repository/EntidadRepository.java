package com.sintad.challenge.repository;

import com.sintad.challenge.model.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad,Integer> {

}
