package com.cefet.ds_petit.dto;

import java.time.LocalDate;

public class EmprestimoVencimentoDTO {

    private String tituloLivro;
    private String nomeEstudante;
    private LocalDate prazoMaximo;

    public EmprestimoVencimentoDTO() {

    }

    public EmprestimoVencimentoDTO(String tituloLivro, String nomeEstudante, LocalDate prazoMaximo) {
        this.tituloLivro = tituloLivro;
        this.nomeEstudante = nomeEstudante;
        this.prazoMaximo = prazoMaximo;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public String getNomeEstudante() {
        return nomeEstudante;
    }

    public LocalDate getPrazoMaximo() {
        return prazoMaximo;
    } 

    

    
    
}