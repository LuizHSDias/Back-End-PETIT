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

import com.cefet.ds_petit.dto.EmprestimoDTO;
import com.cefet.ds_petit.services.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> findById(@PathVariable Long id){
        EmprestimoDTO dto = emprestimoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<List<EmprestimoDTO>> findAll(){
        List<EmprestimoDTO> dtos = emprestimoService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> create(@RequestBody EmprestimoDTO dto){
        EmprestimoDTO novoDTO = emprestimoService.insert(dto);
        return ResponseEntity.status(201).body(novoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id, @RequestBody EmprestimoDTO dto){
        EmprestimoDTO salvoDTO = emprestimoService.update(id, dto);
        return ResponseEntity.ok(salvoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        emprestimoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}