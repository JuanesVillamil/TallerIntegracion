package com.example.ArquiSoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArquiSoft.model.Oferta;
import com.example.ArquiSoft.repository.OfertaRepository;
import com.example.ArquiSoft.model.Subasta;
import com.example.ArquiSoft.model.dao.CrearOfertaDAO;
import com.example.ArquiSoft.repository.SubastaRepository;
import com.example.ArquiSoft.model.Postor;
import com.example.ArquiSoft.repository.PostorRepository;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private SubastaRepository subastaRepository;

    @Autowired
    private PostorRepository postorRepository;
    
    public Oferta crearOferta(CrearOfertaDAO crearOfertaDAO) {
        Oferta oferta = new Oferta(crearOfertaDAO.getValor());
        Postor postor = postorRepository.obtenerPostor(crearOfertaDAO.getPostorId());
        Subasta subasta = subastaRepository.obtenerSubasta(crearOfertaDAO.getSubastaId());
        oferta.setPostor(postor);
        oferta.setSubasta(subasta);
        return ofertaRepository.save(oferta);
    }
}
