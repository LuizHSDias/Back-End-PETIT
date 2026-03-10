package com.cefet.ds_petit.dto;

public class DashboardResumoDTO {

    private long totalLivros;
    private long emprestimosAtivos;
    private long emprestimosAtrasados; 
    private long estudantesAtivos;


    public DashboardResumoDTO() {

    }


    public DashboardResumoDTO(long totalLivros, long emprestimosAtivos, long emprestimosAtrasados, long estudantesAtivos) {
        this.totalLivros = totalLivros;
        this.emprestimosAtivos = emprestimosAtivos;
        this.emprestimosAtrasados = emprestimosAtrasados;
        this.estudantesAtivos = estudantesAtivos;
    }


    public long getTotalLivros() {
        return totalLivros;
    }


    public long getEmprestimosAtivos() {
        return emprestimosAtivos;
    }


    public long getEmprestimosAtrasados() {
        return emprestimosAtrasados;
    }


    public long getEstudantesAtivos() {
        return estudantesAtivos;
    }

    
}