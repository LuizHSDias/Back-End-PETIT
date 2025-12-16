package com.cefet.ds_petit.dto;

import java.time.LocalDateTime;

import com.cefet.ds_petit.entities.Livro;

public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    private Integer ano;
    private String isbn;
    private String genero;
    private Integer quantidadeTotal;
    private Integer quantidadeDisponivel;
    private LocalDateTime dataCadastro;
    private Boolean ativo; 

    public LivroDTO(){

    }

    public LivroDTO(Livro livro){
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.editora = livro.getEditora();
        this.ano = livro.getAno();
        this.isbn = livro.getIsbn();
        this.genero = livro.getGenero();
        this.quantidadeTotal = livro.getQuantidadeTotal();
        this.quantidadeDisponivel = livro.getQuantidadeDisponivel();
        this.dataCadastro = livro.getDataCadastro();
        this.ativo = livro.getAtivo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public Integer getAno() {
        return ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenero(){
        return genero;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    

}