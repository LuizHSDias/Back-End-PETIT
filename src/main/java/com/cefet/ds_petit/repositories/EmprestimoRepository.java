package com.cefet.ds_petit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ds_petit.entities.Emprestimo;

public interface EmprestimoRepository extends JpaRepository <Emprestimo, Long> {

}