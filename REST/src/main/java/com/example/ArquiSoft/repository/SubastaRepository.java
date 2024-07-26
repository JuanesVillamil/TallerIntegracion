package com.example.ArquiSoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ArquiSoft.model.Subasta;

@Repository
public interface SubastaRepository extends JpaRepository<Subasta, Long>{
    
    @Query("SELECT b FROM Subasta b WHERE b.id = :subastaId")
    Subasta obtenerSubasta(@Param("subastaId")Long subastaId);
}

