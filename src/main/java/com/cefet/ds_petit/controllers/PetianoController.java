package com.cefet.ds_petit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.ds_petit.dto.PetianoDTO;
import com.cefet.ds_petit.services.PetianoService;

@RestController
@RequestMapping("/petianos")
public class PetianoController {
    @Autowired
    private PetianoService petianoService;

    @GetMapping("/{id}")
    public ResponseEntity<PetianoDTO> findById(@PathVariable Long id){
        PetianoDTO dto = petianoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PetianoDTO>> findAll(){
        List<PetianoDTO> dtos = petianoService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping 
    public ResponseEntity<PetianoDTO> create(@RequestBody PetianoDTO dto){
        PetianoDTO novo = petianoService.insert(dto);
        return ResponseEntity.status(201).body(novo);
    }

    /* 

    @PutMapping("/{id}")
    public ResponseEntity<PetianoDTO> update(@PathVariable Long id, @RequestBody PetianoDTO dto){
        PetianoDTO salvoDTO = petianoService.update(id, dto);
        return ResponseEntity.ok(salvoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        petianoService.delete(id);
        return ResponseEntity.noContent().build();
    } 

    */

     @GetMapping("/existe")
    public ResponseEntity<Boolean> existsByLogin(@RequestParam String login) {
        boolean existe = petianoService.existsByLogin(login);
        return ResponseEntity.ok(existe);
    }
}