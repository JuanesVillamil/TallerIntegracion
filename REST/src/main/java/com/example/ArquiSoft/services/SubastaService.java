package com.example.ArquiSoft.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArquiSoft.model.Subasta;
import com.example.ArquiSoft.repository.SubastaRepository;

@Service
public class SubastaService {

    @Autowired
    private SubastaRepository subastaRepository;
    
    public List<Subasta> listarSubastas() {
        return subastaRepository.findAll();
    }
}
