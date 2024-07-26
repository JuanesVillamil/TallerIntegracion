package com.example.ArquiSoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ArquiSoft.model.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long>{
    
}
