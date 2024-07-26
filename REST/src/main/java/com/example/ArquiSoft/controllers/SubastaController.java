package com.example.ArquiSoft.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ArquiSoft.model.Subasta;
import com.example.ArquiSoft.services.SubastaService;


@RestController
@RequestMapping("/api/subasta")
public class SubastaController {

    @Autowired
    private SubastaService subastaService;

    @GetMapping("/list")
    public List<Subasta> listarSubastas() {
        return subastaService.listarSubastas();
    }
    
}
