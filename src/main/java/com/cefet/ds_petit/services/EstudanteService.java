package com.cefet.ds_petit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.ds_petit.dto.EstudanteDTO;
import com.cefet.ds_petit.entities.Estudante;
import com.cefet.ds_petit.repositories.EstudanteRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    public EstudanteService(){

    }

    // Listar todos os estudantes ativos 
    @Transactional(readOnly = true)
    public List<EstudanteDTO> findAll(){
        List<Estudante> lista = estudanteRepository.findAll();
        return lista.stream().map(EstudanteDTO::new).toList();
    }

    // Listar paginado (caso queira usar em tecla com paginação)
    @Transactional(readOnly = true)
    public Page<EstudanteDTO> findAll(Pageable pageable){
        Page<Estudante> page = estudanteRepository.findByAtivoTrue(pageable);
        return page.map(EstudanteDTO::new);
    }

  
    // Buscar Estudante por ID
    @Transactional(readOnly = true)
    public EstudanteDTO findById(Long id){
        Estudante estudante = estudanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudante não encontrado com ID: " + id));
        return new EstudanteDTO(estudante);
    }

    // Buscar por nome (para pesquisa no sistema)
    @Transactional(readOnly = true)
    public Page<EstudanteDTO> findByNome(String nome, Pageable pageable){
        Page<Estudante> page = estudanteRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome, pageable);
        return page.map(EstudanteDTO::new);
    }


    // Inserir (Verifica duplicidade de e-mail)
    public EstudanteDTO insert(EstudanteDTO dto){

        if(estudanteRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new RuntimeException("E-mail já cadastrado: " + dto.getEmail());
        }

        Estudante estudante = new Estudante();
        estudante.setNome(dto.getNome());
        estudante.setEmail(dto.getEmail());
        estudante.setCurso(dto.getCurso());
        estudante.setMatricula(dto.getMatricula());

        Estudante salvo = estudanteRepository.save(estudante);
        return new EstudanteDTO(salvo);
    }

    // Atualizar (Verificar duplicidade de e-mail em outro registro)
    public EstudanteDTO update(Long id, EstudanteDTO dto){
        Estudante estudante = estudanteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Estudante não encontrado com ID: " + id));


        boolean emailDuplicado = estudanteRepository.existsByEmailAndIdNot(dto.getEmail(), id);
            if(emailDuplicado){
                throw new RuntimeException("E-mail já cadastrado: " + dto.getEmail());
            }
        
        estudante.setNome(dto.getNome());
        estudante.setEmail(dto.getEmail());
        estudante.setCurso(dto.getCurso());
        estudante.setMatricula(dto.getMatricula());

        Estudante atualizado = estudanteRepository.save(estudante);
        return new EstudanteDTO(atualizado);
    }

    // Remover ID
    public void delete(Long id){
        Estudante estudante = estudanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudante não encontrado com ID: " + id));

        // Evita excluir quem tem empréstimos ativos
        if (estudante.getQuantidadeEmprestimosAtivos() > 0) {
            throw new RuntimeException("Não é possível excluir estudante com empréstimos ativos.");
        }

        estudante.setAtivo(false);
        estudanteRepository.save(estudante);
    } 

    // Contar estudantes ativos 
    @Transactional(readOnly = true)
    public long countAtivos(){
        return estudanteRepository.countByAtivoTrue();
    }
}