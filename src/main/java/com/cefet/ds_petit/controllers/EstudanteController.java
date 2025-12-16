package com.cefet.ds_petit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.ds_petit.dto.EstudanteDTO;
import com.cefet.ds_petit.services.EstudanteService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstudanteDTO> findById(@PathVariable Long id){
        EstudanteDTO dto = estudanteService.findById(id);
        return ResponseEntity.ok(dto);
    }

    // Listar todos (sem paginação)
    @GetMapping
    public ResponseEntity<List<EstudanteDTO>> findAll(){
        List<EstudanteDTO> dtos = estudanteService.findAll();
        return ResponseEntity.ok(dtos);
    }

    // Listar paginado
    @GetMapping("/page")
    public ResponseEntity<Page<EstudanteDTO>> findAllPaged(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "nome") String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<EstudanteDTO> result = estudanteService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    // Buscar por nome (paginado)
    @GetMapping("/search")
    public ResponseEntity<Page<EstudanteDTO>> findByNome(
        @RequestParam(defaultValue = "") String nome,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "nome") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<EstudanteDTO> result = estudanteService.findByNome(nome, pageable);
        return ResponseEntity.ok(result);
    }
        
    // Criar novo estudante
    @PostMapping
    public ResponseEntity<EstudanteDTO> create(@RequestBody EstudanteDTO dto){
        EstudanteDTO novoDTO = estudanteService.insert(dto);
        return ResponseEntity.status(201).body(novoDTO);
    }

    // Atualizar
    @PutMapping("{id}")
    public ResponseEntity<EstudanteDTO> update(@PathVariable Long id, @RequestBody EstudanteDTO dto){
        EstudanteDTO salvoDTO = estudanteService.update(id, dto);
        return ResponseEntity.ok(salvoDTO);
    }

    // Excluir (soft delete)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estudanteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Contar estudantes ativos
    @GetMapping("/ativos/count")
    public ResponseEntity<Long> countAtivos(){
        long count = estudanteService.countAtivos();
        return ResponseEntity.ok(count);
    }

    // Tratamento de exceções
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
}