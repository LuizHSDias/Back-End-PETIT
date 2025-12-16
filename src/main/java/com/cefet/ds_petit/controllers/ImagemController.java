package com.cefet.ds_petit.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cefet.ds_petit.dto.ImagemDTO;
import com.cefet.ds_petit.services.ImagemService;

@RestController
@RequestMapping("/imagens")
public class ImagemController {
    @Autowired
	private ImagemService arquivoService;
	
	@PostMapping("/upload")
    public ResponseEntity<ImagemDTO> uploadImagem(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        
        try {
            ImagemDTO arquivoDTO = arquivoService.uploadImagem(file);
            return new ResponseEntity<>(arquivoDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // erros de tipo de arquivo inválido
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (IOException e) {
            // erros de I/O ao salvar o arquivo
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            // outros erros inesperados
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }	
}
