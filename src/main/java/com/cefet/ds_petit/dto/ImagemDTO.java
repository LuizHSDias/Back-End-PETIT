package com.cefet.ds_petit.dto;

import com.cefet.ds_petit.entities.Imagem;

public class ImagemDTO {
    private Long id;
    private String nome;

    public ImagemDTO() {

    }

    public ImagemDTO(Imagem arquivo) {
        this.id = arquivo.getId();
        this.nome = arquivo.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUrlImagem() {
		return "http://localhost:8080/imagens/" + nome;
	}

}