package com.example.ArquiSoft.model.dao;

import org.springframework.web.bind.annotation.RequestParam;

public class CrearOfertaDAO {

    private double valor;
    private Long subastaId; 
    private Long postorId;


    public CrearOfertaDAO() {
    }

    public CrearOfertaDAO(double valor, Long subastaId, Long postorId) {
        this.valor = valor;
        this.subastaId = subastaId;
        this.postorId = postorId;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getSubastaId() {
        return this.subastaId;
    }

    public void setSubastaId(Long subastaId) {
        this.subastaId = subastaId;
    }

    public Long getPostorId() {
        return this.postorId;
    }

    public void setPostorId(Long postorId) {
        this.postorId = postorId;
    }

}