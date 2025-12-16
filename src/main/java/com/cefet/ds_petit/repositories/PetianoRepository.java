package com.cefet.ds_petit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ds_petit.entities.Petiano;

public interface PetianoRepository extends JpaRepository<Petiano, Long> {

    boolean existsByLogin(String login);

    Optional<Petiano> findByLogin(String login);
}