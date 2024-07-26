package com.example.ArquiSoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ArquiSoft.model.Oferta;
import com.example.ArquiSoft.model.dao.CrearOfertaDAO;
import com.example.ArquiSoft.services.OfertaService;

@RestController
@RequestMapping("/api/oferta")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @PostMapping("/ofertar")
    public ResponseEntity<Oferta> hacerOferta(@RequestBody CrearOfertaDAO crearOfertaDAO) {
        Oferta oferta = ofertaService.crearOferta(crearOfertaDAO);
        return ResponseEntity.status(HttpStatus.CREATED).body(oferta);
    }

}
