package com.cefet.ds_petit.dto;

import com.cefet.ds_petit.entities.Petiano;

public class PetianoMinDTO {
    private Long id;
    private String nome; 

    public PetianoMinDTO(){

    }

    public PetianoMinDTO(Petiano petiano){
        this.id = petiano.getId();
        this.nome = petiano.getEstudante().getNome();
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }
}