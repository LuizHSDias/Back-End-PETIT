package com.cefet.ds_petit.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cefet.ds_petit.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // BUSCAR POR ISBN
    Optional<Livro> findByIsbn(String isbn);

    // BUSCAR POR TÍTULO (CASE SENSITIVE)
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    // BUSCAR POR AUTOR (CASE SENSITIVE)
    List<Livro> findByAutorContainingIgnoreCase(String autor);

    // BUSCAR POR GÊNERO
    List<Livro> findByGenero(String genero);

    // BUSCAR LIVROS DISPONÍVEIS
    List<Livro> findByQuantidadeDisponivelGreaterThanAndAtivoTrue(int quantidade);

    // BUSCAR LIVROS DISPONÍVEIS COM PAGINAÇÃO. 
    Page<Livro> findByQuantidadeDisponivelGreaterThanAndAtivoTrue(int quantidade, Pageable pageable);

    // BUSCAR TODOS OS LIVROS ATIVOS COM PAGINAÇÃO.
    Page<Livro> findByAtivoTrue(Pageable pageable);

    // VERIFICAR SE ISBN EXISTE (EXCLUINDO O PRÓPRIO LIVRO)
    @Query("SELECT COUNT(l) > 0 FROM Livro l WHERE l.isbn = :isbn AND (:id IS NULL OR l.id <> :id)")
    boolean existsByIsbnAndIdNot(String isbn, Long id);

    // CONTAR LIVROS DISPONÍVEIS. 
    @Query("SELECT COUNT(l) FROM Livro l WHERE l.quantidadeDisponivel > 0 AND l.ativo = true")
    long countLivrosDisponiveis();

    // CONTAR TOTAL DE LIVROS. 
    @Query("SELECT COUNT(l) FROM Livro l WHERE l.ativo = true")
    long countLivrosAtivos();

    // BUSCAR LIVROS MAIS EMPRESTADOS. 
    @Query("""
        SELECT l FROM Livro l 
        JOIN l.emprestimos e
        WHERE l.ativo = true
        GROUP BY l.id
        ORDER BY COUNT(e.id) DESC
        """)
    List<Livro> findLivrosMaisEmprestados(Pageable pageable);

    // BUSCAR LIVROS POR GÊNERO COM CONTAGEM
    @Query("""
        SELECT l.genero, COUNT(l)
        FROM Livro l
        WHERE l.ativo = true
        GROUP BY l.genero
        ORDER BY COUNT(l) DESC
        """)
    List<Object[]> countLivrosPorGenero();
}