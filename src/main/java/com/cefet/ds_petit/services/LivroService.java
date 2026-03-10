package com.cefet.ds_petit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ds_petit.dto.LivroDTO;
import com.cefet.ds_petit.entities.Livro;
import com.cefet.ds_petit.repositories.LivroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public LivroService(){

    }

    // Listar
    public List<LivroDTO> findAll(){
        List<Livro> lista = livroRepository.findAll();
        return lista.stream().map(LivroDTO::new).toList();
    }

    // Buscar ID
    public LivroDTO findById(Long id){
        Livro livro = livroRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Livro não encontrado com ID: " + id));
        return new LivroDTO(livro);
    }

    // Inserir 
    public LivroDTO insert(LivroDTO dto){
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setEditora(dto.getEditora());
        livro.setAno(dto.getAno());
        livro.setIsbn(dto.getIsbn());
        livro.setGenero(dto.getGenero());
        Livro salvo = livroRepository.save(livro);
        return new LivroDTO(salvo);
    }

    // Atualizar
    public LivroDTO update(Long id, LivroDTO dto){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com ID: " + id));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setEditora(dto.getEditora());
        livro.setAno(dto.getAno());
        livro.setIsbn(dto.getIsbn());
        livro.setGenero(dto.getGenero());
        Livro atualizado = livroRepository.save(livro);
        return new LivroDTO(atualizado);
    }

    // Remover ID
    public void delete(Long id){
        if(!livroRepository.existsById(id)){
            throw new EntityNotFoundException("Livro não encontrado com ID: " + id);
        }
        livroRepository.deleteById(id);
    }

}