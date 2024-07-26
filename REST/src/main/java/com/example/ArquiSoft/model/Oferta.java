package com.example.ArquiSoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "subasta_id")
    @JsonIgnore
    private Subasta subasta;
    
    @ManyToOne
    @JoinColumn(name = "postor_id")
    @JsonIgnore
    private Postor postor;

    public Oferta() { 
    }

    public Oferta(Double valor) {
        this.valor = valor;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Subasta getSubasta() {
        return this.subasta;
    }

    public void setSubasta(Subasta subasta) {
        this.subasta = subasta;
    }

    public Postor getPostor() {
        return this.postor;
    }

    public void setPostor(Postor postor) {
        this.postor = postor;
    }

}
