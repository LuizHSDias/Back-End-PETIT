package com.cefet.ds_petit.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cefet.ds_petit.entities.Emprestimo;
import com.cefet.ds_petit.entities.StatusEmprestimo;

import java.time.LocalDate;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // BUSCAR EMPRÉSTIMO POR STATUS
    List<Emprestimo> findByStatus(StatusEmprestimo status);

    // BUSCAR EMPRÉSTIMOS POR STATUS COM PAGINAÇÃO
    Page<Emprestimo> findByStatus(StatusEmprestimo status, Pageable pageable);

    // BUSCAR EMPRÉSTIMOS POR ESTUDANTE
    List<Emprestimo> findByEstudanteId(Long estudanteId);

    // BUSCAR EMPRÉSTIMOS POR ESTUDANTE E STATUS
    List<Emprestimo> findByEstudanteIdAndStatus(Long estudanteId, StatusEmprestimo status);

    // BUSCAR EMPRÉSTIMOS POR LIVRO
    List<Emprestimo> findByLivroId(Long livroId);

    // BUSCAR EMPRÉSTIMOS ATIVOS POR ESTUDANTE
    @Query("""
        SELECT e
        FROM Emprestimo e
        WHERE e.estudante.id = :estudanteId
        AND e.status = 'ATIVO'
    """)
    List<Emprestimo> findEmprestimosAtivosPorEstudante(
            @Param("estudanteId") Long estudanteId);

    // BUSCAR EMPRÉSTIMOS ATRASADOS
    @Query("""
        SELECT e
        FROM Emprestimo e
        WHERE e.status = 'ATIVO'
        AND e.prazoMaximo < CURRENT_DATE
    """)
    List<Emprestimo> findEmprestimosAtrasados();

    // BUSCAR EMPRÉSTIMOS ATRASADOS COM PAGINAÇÃO
    @Query("""
        SELECT e
        FROM Emprestimo e
        WHERE e.status = 'ATIVO'
        AND e.prazoMaximo < CURRENT_DATE
    """)
    Page<Emprestimo> findEmprestimosAtrasados(Pageable pageable);

    // BUSCAR EMPRÉSTIMOS POR PERÍODO
    @Query("""
        SELECT e
        FROM Emprestimo e
        WHERE e.dataEmprestimo BETWEEN :dataInicio AND :dataFim
    """)
    List<Emprestimo> findEmprestimosPorPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);

    // CONTAR EMPRÉSTIMOS POR STATUS
    long countByStatus(StatusEmprestimo status);

    // CONTAR EMPRÉSTIMOS ATRASADOS
    @Query("""
        SELECT COUNT(e)
        FROM Emprestimo e
        WHERE e.status = 'ATIVO'
        AND e.prazoMaximo < CURRENT_DATE
    """)
    long countEmprestimosAtrasados();

    // CONTAR EMPRÉSTIMOS ATIVOS POR ESTUDANTE
    @Query("""
        SELECT COUNT(e)
        FROM Emprestimo e
        WHERE e.estudante.id = :estudanteId
        AND e.status = 'ATIVO'
    """)
    long countEmprestimosAtivosPorEstudante(
            @Param("estudanteId") Long estudanteId);

    // BUSCAR EMPRÉSTIMOS QUE VENCEM EM UMA DATA
    @Query("""
        SELECT e
        FROM Emprestimo e
        WHERE e.status = 'ATIVO'
        AND e.prazoMaximo = :dataVencimento
    """)
    List<Emprestimo> findEmprestimosVencendoEm(
            @Param("dataVencimento") LocalDate dataVencimento);

    // BUSCAR EMPRÉSTIMOS QUE VENCEM NOS PRÓXIMOS DIAS
    @Query("""
        SELECT e
        FROM Emprestimo e
        WHERE e.status = 'ATIVO'
        AND e.prazoMaximo BETWEEN CURRENT_DATE AND :dataLimite
    """)
    List<Emprestimo> findEmprestimosVencendoNosProximosDias(
            @Param("dataLimite") LocalDate dataLimite);

    // ESTATÍSTICAS DE EMPRÉSTIMOS POR MÊS (LocalDate compatível)
    @Query("""
        SELECT MONTH(e.dataEmprestimo), COUNT(e)
        FROM Emprestimo e
        GROUP BY MONTH(e.dataEmprestimo)
        ORDER BY MONTH(e.dataEmprestimo)
    """)
    List<Object[]> findEstatisticasEmprestimosPorMes();

    // BUSCAR EMPRÉSTIMOS COM MULTA
    @Query("SELECT e FROM Emprestimo e WHERE e.multa > 0")
    List<Emprestimo> findEmprestimosComMulta();

    // VERIFICAR SE ESTUDANTE TEM EMPRÉSTIMO ATIVO DO LIVRO
    @Query("""
        SELECT COUNT(e) > 0
        FROM Emprestimo e
        WHERE e.estudante.id = :estudanteId
        AND e.livro.id = :livroId
        AND e.status = 'ATIVO'
    """)
    boolean existsEmprestimoAtivoPorEstudanteELivro(
            @Param("estudanteId") Long estudanteId,
            @Param("livroId") Long livroId);

    // BUSCAR EMPRÉSTIMOS RECENTES
    @Query("SELECT e FROM Emprestimo e ORDER BY e.dataEmprestimo DESC")
    Page<Emprestimo> findEmprestimosRecentes(Pageable pageable);
}