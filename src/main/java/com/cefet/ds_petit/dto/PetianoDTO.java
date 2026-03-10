package com.cefet.ds_petit.dto;

import java.time.LocalDate;

import com.cefet.ds_petit.entities.Estudante;
import com.cefet.ds_petit.entities.NivelAcesso;
import com.cefet.ds_petit.entities.Petiano;
import com.cefet.ds_petit.entities.TipoPetiano;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PetianoDTO {
    private Long id;
    private LocalDate dataEntrada;
    private Estudante estudante;
    private TipoPetiano tipo;
    private String login;
    // Impede que a Senha seja exposta
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private NivelAcesso nivelAcesso; 
    private String urlImagem;
    
    public PetianoDTO(){

    }

    public PetianoDTO(Petiano petiano){
        this.id = petiano.getId();
        this.dataEntrada = petiano.getDataEntrada();
        this.estudante = petiano.getEstudante();
        this.tipo = petiano.getTipo();
        this.login = petiano.getLogin();
        this.senha = petiano.getSenha();
        this.nivelAcesso = petiano.getNivelAcesso();
        this.urlImagem = petiano.getUrlImagem();

    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public TipoPetiano getTipo() {
        return tipo;
    }

    public String getLogin(){
        return login;
    }

    public String getSenha(){
        return senha;
    }
    
    public NivelAcesso getNivelAcesso(){
        return nivelAcesso;
    }

    public String getUrlImagem(){
        return urlImagem;
    }
}