package com.cefet.ds_petit.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cefet.ds_petit.entities.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {

    // BUSCAR POR E-MAIL
    Optional<Estudante> findByEmail(String email);

    // BUSCAR POR NOME (CASE INSENSITIVE)
    List<Estudante> findByNomeContainingIgnoreCase(String nome);

    // BUSCAR ESTUDANTES ATIVOS
    List<Estudante> findByAtivoTrue();

    // BUSCAR ESTUDANTES ATIVOS COM PAGINAÇÃO
    Page<Estudante> findByAtivoTrue(Pageable pageable);

    // BUSCAR POR NOME + ATIVO COM PAGINAÇÃO
    @Query("""
        SELECT u
        FROM Estudante u
        WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))
        AND u.ativo = true
    """)
    Page<Estudante> findByNomeContainingIgnoreCaseAndAtivoTrue(
        @Param("nome") String nome,
        Pageable pageable
    );

    // VERIFICA SE E-MAIL EXISTE, EXCLUINDO O PRÓPRIO ID
    @Query("""
        SELECT COUNT(u) > 0
        FROM Estudante u
        WHERE u.email = :email
        AND (:id IS NULL OR u.id != :id)
    """)
    boolean existsByEmailAndIdNot(
        @Param("email") String email,
        @Param("id") Long id
    );

    // CONTAR ESTUDANTES ATIVOS
    long countByAtivoTrue();

    // BUSCAR ESTUDANTES COM EMPRÉSTIMOS ATIVOS
    @Query("""
        SELECT DISTINCT u
        FROM Estudante u
        JOIN Emprestimo e ON e.estudante.id = u.id
        WHERE e.status = 'ATIVO'
        AND u.ativo = true
    """)
    List<Estudante> findEstudantesComEmprestimosAtivos();

    // BUSCAR ESTUDANTES COM EMPRÉSTIMOS ATRASADOS
    @Query("""
        SELECT DISTINCT u
        FROM Estudante u
        JOIN Emprestimo e ON e.estudante.id = u.id
        WHERE e.status = 'ATRASADO'
        AND u.ativo = true
    """)
    List<Estudante> findEstudantesComEmprestimosAtrasados();
}