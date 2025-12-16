package com.cefet.ds_petit.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cefet.ds_petit.entities.Emprestimo;
import com.cefet.ds_petit.entities.Estudante;
import com.cefet.ds_petit.entities.Livro;
import com.cefet.ds_petit.entities.Petiano;
import com.cefet.ds_petit.entities.StatusEmprestimo;

public class EmprestimoDTO {
    private Long id;
    private Livro livro;
    private Petiano petiano;
    private Estudante estudante;
    private LocalDate dataEmprestimo;
    private LocalDate prazoMaximo;
    private LocalDate dataDevolucao;
    private StatusEmprestimo status;
    private String observacoes;
    private BigDecimal multa;

    public EmprestimoDTO(){

    }

    public EmprestimoDTO(Emprestimo emprestimo){
        this.id = emprestimo.getId();
        this.livro = emprestimo.getLivro();
        this.petiano = emprestimo.getPetiano();
        this.estudante = emprestimo.getEstudante();
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.prazoMaximo = emprestimo.getPrazoMaximo();
        this.dataDevolucao = emprestimo.getDataDevolucao();
        this.status = emprestimo.getStatus();
        this.observacoes = emprestimo.getObservacoes();
        this.multa = emprestimo.getMulta();
    }

    public Long getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public Petiano getPetiano() {
        return petiano;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getPrazoMaximo() {
        return prazoMaximo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public BigDecimal getMulta() {
        return multa;
    }
    
}