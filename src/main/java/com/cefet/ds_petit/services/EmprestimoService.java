package com.cefet.ds_petit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ds_petit.dto.EmprestimoDTO;
import com.cefet.ds_petit.entities.Emprestimo;
import com.cefet.ds_petit.repositories.EmprestimoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoService(){

    }

    // Listar
    public List<EmprestimoDTO> findAll(){
        List<Emprestimo> lista = emprestimoRepository.findAll();
        return lista.stream().map(EmprestimoDTO::new).toList();
    }

    // Buscar ID
    public EmprestimoDTO findById(Long id){
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado com ID: " + id));
        return new EmprestimoDTO(emprestimo);
    }

    // Inserir 
    public EmprestimoDTO insert(EmprestimoDTO dto){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(dto.getLivro());
        emprestimo.setPetiano(dto.getPetiano());
        emprestimo.setEstudante(dto.getEstudante());
        emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        emprestimo.setPrazoMaximo(dto.getPrazoMaximo());
        emprestimo.setDataDevolucao(dto.getDataDevolucao());
        Emprestimo salvo = emprestimoRepository.save(emprestimo);
        return new EmprestimoDTO(salvo);
    }

    // Atualizar 
    public EmprestimoDTO update(Long id, EmprestimoDTO dto){
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado com ID: " + id));
        emprestimo.setLivro(dto.getLivro());
        emprestimo.setPetiano(dto.getPetiano());
        emprestimo.setEstudante(dto.getEstudante());
        emprestimo.setDataEmprestimo(dto.getDataEmprestimo());
        emprestimo.setPrazoMaximo(dto.getPrazoMaximo());
        emprestimo.setDataDevolucao(dto.getDataDevolucao());
        Emprestimo atualizado = emprestimoRepository.save(emprestimo);
        return new EmprestimoDTO(atualizado);
    }

    // Remover ID
    public void delete(Long id){
        if(!emprestimoRepository.existsById(id)){
            throw new EntityNotFoundException("Empréstimo não encontrado com ID: " + id);
        }
        emprestimoRepository.deleteById(id);
    }
}