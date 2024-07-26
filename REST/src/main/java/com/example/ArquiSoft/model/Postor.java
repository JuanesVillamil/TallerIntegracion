package com.example.ArquiSoft.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Postor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "postor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Oferta> ofertas = new ArrayList<>();

    public Postor() { 
    }

    public Postor(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
