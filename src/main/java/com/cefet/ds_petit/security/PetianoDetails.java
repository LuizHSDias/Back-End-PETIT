package com.cefet.ds_petit.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cefet.ds_petit.entities.Petiano;

public class PetianoDetails implements UserDetails  {
    
    private final Petiano petiano;

    public PetianoDetails(Petiano petiano){
        this.petiano = petiano;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + petiano.getNivelAcesso().name()));
    }

    @Override
    public String getPassword() {return petiano.getSenha(); }

    @Override
    public String getUsername() {return petiano.getLogin(); }

    @Override
    public boolean isAccountNonExpired() { return true ;}

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
