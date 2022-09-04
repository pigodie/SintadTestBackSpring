package com.sintad.challenge.repository;

import com.sintad.challenge.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento,Integer> {
}
