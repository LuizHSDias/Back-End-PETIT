package com.cefet.ds_petit.dto;

import com.cefet.ds_petit.entities.Estudante;

public class EstudanteDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String curso;
    private String matricula;
    private boolean ativo;
    private int quantidadeEmprestimosAtivos;

    public EstudanteDTO(){

    }

    public EstudanteDTO(Estudante estudante){
        this.id = estudante.getId();
        this.nome = estudante.getNome();
        this.email = estudante.getEmail();
        this.telefone = estudante.getTelefone();
        this.curso = estudante.getCurso();
        this.matricula = estudante.getMatricula();
        this.ativo = estudante.isAtivo();
        this.quantidadeEmprestimosAtivos = estudante.getQuantidadeEmprestimosAtivos();
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getCurso(){
        return curso;
    }

    public String getMatricula(){
        return matricula;
    }

    public boolean isAtivo(){
        return ativo;
    }

    public int getQuantidadeEmprestimosAtivos(){
        return quantidadeEmprestimosAtivos;
    }
    
}