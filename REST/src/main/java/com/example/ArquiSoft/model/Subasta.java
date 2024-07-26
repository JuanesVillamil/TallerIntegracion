package com.example.ArquiSoft.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Subasta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombreProducto;
    private int tiempoSubasta;

    @OneToMany(mappedBy = "subasta", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Oferta> ofertas = new ArrayList<>();

    public Subasta() { 
    }

    public Subasta(String nombreProducto, int tiempoSubasta) {
        this.nombreProducto = nombreProducto;
        this.tiempoSubasta = tiempoSubasta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return this.nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getTiempoSubasta() {
        return this.tiempoSubasta;
    }

    public void setTiempoSubasta(int tiempoSubasta) {
        this.tiempoSubasta = tiempoSubasta;
    }

    public List<Oferta> getOfertas() {
        return this.ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public void agregarOferta(Oferta oferta){
        this.ofertas.add(oferta);
    }
    
}
