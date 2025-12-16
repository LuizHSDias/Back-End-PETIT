package com.cefet.ds_petit.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cefet.ds_petit.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByAutorContainingIgnoreCase(String autor);

    List<Livro> findByGenero(String genero);

    List<Livro> findByQuantidadeDisponivelGreaterThanAndAtivoTrue(int quantidade);

    Page<Livro> findByQuantidadeDisponivelGreaterThanAndAtivoTrue(int quantidade, Pageable pageable);

    Page<Livro> findByAtivoTrue(Pageable pageable);

    @Query("SELECT COUNT(l) > 0 FROM Livro l WHERE l.isbn = :isbn AND (:id IS NULL OR l.id <> :id)")
    boolean existsByIsbnAndIdNot(String isbn, Long id);

    @Query("SELECT COUNT(l) FROM Livro l WHERE l.quantidadeDisponivel > 0 AND l.ativo = true")
    long countLivrosDisponiveis();

    @Query("SELECT COUNT(l) FROM Livro l WHERE l.ativo = true")
    long countLivrosAtivos();

    @Query("""
        SELECT l FROM Livro l 
        LEFT JOIN l.emprestimos e
        WHERE l.ativo = true
        GROUP BY l.id
        ORDER BY COUNT(e.id) DESC
        """)
    List<Livro> findLivrosMaisEmprestados(Pageable pageable);

    @Query("""
        SELECT l.genero, COUNT(l)
        FROM Livro l
        WHERE l.ativo = true
        GROUP BY l.genero
        ORDER BY COUNT(l) DESC
        """)
    List<Object[]> countLivrosPorGenero();
}
