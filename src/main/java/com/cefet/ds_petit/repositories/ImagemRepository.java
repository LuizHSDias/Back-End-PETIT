package com.cefet.ds_petit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ds_petit.entities.Imagem;

public interface ImagemRepository extends JpaRepository <Imagem, Long> {

}