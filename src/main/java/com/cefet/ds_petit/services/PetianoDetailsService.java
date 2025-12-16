package com.cefet.ds_petit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cefet.ds_petit.entities.Petiano;
import com.cefet.ds_petit.repositories.PetianoRepository;
import com.cefet.ds_petit.security.PetianoDetails;

@Service
public class PetianoDetailsService implements UserDetailsService {

    @Autowired
    private PetianoRepository petianoRepository;

    @Override
    public PetianoDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Petiano petiano = petianoRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Petiano não encontrado: " + login));
        return new PetianoDetails(petiano);
    }
    

}