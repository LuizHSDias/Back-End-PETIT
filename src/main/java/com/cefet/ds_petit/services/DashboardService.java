package com.cefet.ds_petit.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ds_petit.dto.DashboardResumoDTO;
import com.cefet.ds_petit.dto.EmprestimoVencimentoDTO;
import com.cefet.ds_petit.dto.GraficoDTO;
import com.cefet.ds_petit.entities.StatusEmprestimo;
import com.cefet.ds_petit.repositories.EmprestimoRepository;
import com.cefet.ds_petit.repositories.EstudanteRepository;
import com.cefet.ds_petit.repositories.LivroRepository;

@Service
public class DashboardService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    // ==========================
    // CARDS DO TOPO (RESUMO)
    // ==========================
    public DashboardResumoDTO obterResumo() {

        long totalLivros = livroRepository.countLivrosAtivos();
        long emprestimosAtivos = emprestimoRepository.countByStatus(StatusEmprestimo.ATIVO);
        long emprestimosAtrasados = emprestimoRepository.countEmprestimosAtrasados();
        long estudantesAtivos = estudanteRepository.countByAtivoTrue();

        return new DashboardResumoDTO(
                totalLivros,
                emprestimosAtivos,
                emprestimosAtrasados,
                estudantesAtivos
        );
    }

    // ==========================
    // EMPRÉSTIMOS VENCENDO EM BREVE
    // ==========================
    public List<EmprestimoVencimentoDTO> emprestimosVencendoEmBreve(int dias) {

        LocalDate dataLimite = LocalDate.now().plusDays(dias);

        return emprestimoRepository
                .findEmprestimosVencendoNosProximosDias(dataLimite)
                .stream()
                .map(e -> new EmprestimoVencimentoDTO(
                        e.getLivro().getTitulo(),
                        e.getEstudante().getNome(),
                        e.getPrazoMaximo()
                ))
                .toList();
    }

    // ==========================
    // LIVROS MAIS EMPRESTADOS
    // ==========================
    public List<GraficoDTO> livrosMaisEmprestados(int limite) {

        return livroRepository
                .findLivrosMaisEmprestados(
                        org.springframework.data.domain.PageRequest.of(0, limite)
                )
                .stream()
                .map(livro -> new GraficoDTO(
                        livro.getTitulo(),
                        livro.getEmprestimos().size()
                ))
                .toList();
    }

    // ==========================
    // DISTRIBUIÇÃO POR GÊNERO
    // ==========================
    public List<GraficoDTO> distribuicaoPorGenero() {

        return livroRepository
                .countLivrosPorGenero()
                .stream()
                .map(obj -> new GraficoDTO(
                        (String) obj[0],
                        ((Number) obj[1]).longValue()
                ))
                .toList();
    }

    // ==========================
    // HISTÓRICO MENSAL DE EMPRÉSTIMOS
    // ==========================
    public List<GraficoDTO> historicoMensal() {

        return emprestimoRepository
                .findEstatisticasEmprestimosPorMes()
                .stream()
                .map(obj -> new GraficoDTO(
                        "Mês " + ((Number) obj[0]).intValue(),
                        ((Number) obj[1]).longValue()
                ))
                .toList();
    }
}
