package com.example.ArquiSoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ArquiSoft.model.Postor;

@Repository
public interface PostorRepository extends JpaRepository<Postor, Long>{

    @Query("SELECT b FROM Postor b WHERE b.id = :postorId")
    Postor obtenerPostor(@Param("postorId")Long postorId);
}
