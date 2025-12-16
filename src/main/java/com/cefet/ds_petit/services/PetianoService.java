package com.cefet.ds_petit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cefet.ds_petit.dto.PetianoDTO;
import com.cefet.ds_petit.entities.Petiano;
import com.cefet.ds_petit.repositories.PetianoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PetianoService {
    @Autowired
    private PetianoRepository petianoRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

    public PetianoService(){

    }

    // Listar
    public List<PetianoDTO> findAll(){
        List<Petiano> lista = petianoRepository.findAll();
        return lista.stream().map(PetianoDTO::new).toList();
    }

    // Buscar ID
    public PetianoDTO findById(Long id){
        Petiano objeto = petianoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Petiano não encontrado com ID " + id));
        return new PetianoDTO(objeto);
    }

    // Inserir 
    public PetianoDTO insert(PetianoDTO dto){
        if(petianoRepository.existsByLogin(dto.getLogin())){
            throw new IllegalArgumentException("Login já existe");
        }

        Petiano petiano = new Petiano();
        petiano.setLogin(dto.getLogin());
        petiano.setDataEntrada(dto.getDataEntrada());
        petiano.setEstudante(dto.getEstudante());
        petiano.setTipo(dto.getTipoPetiano());
        petiano.setSenha(passwordEncoder.encode(dto.getSenha()));
        petiano.setNivelAcesso(dto.getNivelAcesso());
        petiano.setUrlImagem(dto.getUrlImagem());
        
        Petiano salvo = petianoRepository.save(petiano);
        return new PetianoDTO(salvo);
    }

    // Atualizar
    public PetianoDTO update(Long id, PetianoDTO dto){
        Petiano petiano = petianoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Petiano não encontrado com ID: " + id));
        
        petiano.setSenha(dto.getSenha());

        Petiano atualizado = petianoRepository.save(petiano);
        return new PetianoDTO(atualizado);
    }

    // Remover ID
    public void delete(Long id){
        if(!petianoRepository.existsById(id)){
            throw new EntityNotFoundException("Petiano não encontrado com ID: " + id);
        }
        petianoRepository.deleteById(id);
    }

    // Verifica login   
    public boolean existsByLogin(String login) {
        return petianoRepository.existsByLogin(login);
    }
}
