package com.cefet.ds_petit.dto;

public class GraficoDTO {

    private String label;
    private long valor;

    public GraficoDTO() {

    }

    public GraficoDTO(String label, long valor) {
        this.label = label;
        this.valor = valor;
    }

    public String getLabel() {
        return label;
    }

    public long getValor() {
        return valor;
    }

    

    
    
}