package com.cefet.ds_petit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.ds_petit.dto.LivroDTO;
import com.cefet.ds_petit.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id){
        LivroDTO dto = livroService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(){
        List<LivroDTO> dtos = livroService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> create(@RequestBody LivroDTO dto){
        LivroDTO novoDTO = livroService.insert(dto);
        return ResponseEntity.status(201).body(novoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id, @RequestBody LivroDTO dto){
        LivroDTO salvoDTO = livroService.update(id, dto);
        return ResponseEntity.ok(salvoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}