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
		return "https://ds-guia12.onrender.com/imagens/" + nome;
	}

}