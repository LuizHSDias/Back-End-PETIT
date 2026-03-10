package com.cefet.ds_petit.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.ds_petit.dto.DashboardResumoDTO;
import com.cefet.ds_petit.dto.EmprestimoVencimentoDTO;
import com.cefet.ds_petit.dto.GraficoDTO;
import com.cefet.ds_petit.services.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // ==========================
    // CARDS DO TOPO (RESUMO)
    // ==========================
    @GetMapping("/resumo")
    public DashboardResumoDTO obterResumo() {
        return dashboardService.obterResumo();
    }

    // ==========================
    // EMPRÉSTIMOS VENCENDO EM BREVE
    // ==========================
    @GetMapping("/vencendo")
    public List<EmprestimoVencimentoDTO> emprestimosVencendoEmBreve(
            @RequestParam(defaultValue = "5") int dias) {

        return dashboardService.emprestimosVencendoEmBreve(dias);
    }

    // ==========================
    // LIVROS MAIS EMPRESTADOS
    // ==========================
    @GetMapping("/livros-mais-emprestados")
    public List<GraficoDTO> livrosMaisEmprestados(
            @RequestParam(defaultValue = "5") int limite) {

        return dashboardService.livrosMaisEmprestados(limite);
    }

    // ==========================
    // DISTRIBUIÇÃO POR GÊNERO
    // ==========================
    @GetMapping("/generos")
    public List<GraficoDTO> distribuicaoPorGenero() {
        return dashboardService.distribuicaoPorGenero();
    }

    // ==========================
    // HISTÓRICO MENSAL
    // ==========================
    @GetMapping("/historico-mensal")
    public List<GraficoDTO> historicoMensal() {
        return dashboardService.historicoMensal();
    }
}