package com.example.ArquiSoft.init;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ArquiSoft.model.*;
import com.example.ArquiSoft.repository.*;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private PostorRepository postorRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private SubastaRepository subastaRepository;

    @Override
    public void run(String... args) throws Exception {

        generarDatos(5);
    }

    private void generarDatos(int cantidad) {
        List<Postor> postores = new ArrayList<>();
        List<Subasta> subastas = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < cantidad; i++) {
            String nombre = "Usuario " + i;
            String nombreProducto = "Producto " + i;
            int tiempoSubasta = rand.nextInt(2) + 1;
            Postor postor = new Postor(nombre);
            Subasta subasta = new Subasta(nombreProducto, tiempoSubasta);

            subasta = subastaRepository.save(subasta);
            postor = postorRepository.save(postor);

            Oferta oferta = new Oferta(rand.nextDouble(1000));
            oferta.setPostor(postor);
            oferta.setSubasta(subasta);
            postor.agregarOferta(oferta);
            subasta.agregarOferta(oferta);
            ofertaRepository.save(oferta);

            postores.add(postor);
            subastas.add(subasta);
        }
    }

}