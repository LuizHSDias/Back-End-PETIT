package com.cefet.ds_petit.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petiano_id", nullable = false)
    private Petiano petiano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @NotNull
    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEmprestimo; 

    @NotNull
    @Column(name = "prazo_maximo", nullable = false)
    private LocalDate prazoMaximo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEmprestimo status = StatusEmprestimo.ATIVO;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "multa", precision = 10, scale = 2)
    private BigDecimal multa = BigDecimal.ZERO;
    
    // CONSTRUTORES 

    public Emprestimo() {

    }

    public Emprestimo(Livro livro, Petiano petiano, Estudante estudante, LocalDate dataEmprestimo, LocalDate prazoMaximo, String observacoes) {
       
        this.livro = livro;
        this.petiano = petiano;
        this.estudante = estudante;
        this.dataEmprestimo = dataEmprestimo;
        this.prazoMaximo = prazoMaximo;
        this.status = StatusEmprestimo.ATIVO;
        this.observacoes = observacoes;
        this.multa = BigDecimal.ZERO;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Petiano getPetiano() {
        return petiano;
    }

    public void setPetiano(Petiano petiano) {
        this.petiano = petiano;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getPrazoMaximo() {
        return prazoMaximo;
    }

    public void setPrazoMaximo(LocalDate prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    // MÉTODOS AUXILIARES 
    public boolean isAtrasado(){
        return status == StatusEmprestimo.ATIVO && LocalDate.now().isAfter(prazoMaximo);
    }

    public void devolver() {
    //  this.dataDevolucao = LocalDateTime.now();
        this.status = StatusEmprestimo.DEVOLVIDO;
    //  this.livro = incrementarDisponivel();
    }

    public void marcarComoAtrasado() {
        if(status == StatusEmprestimo.ATIVO && isAtrasado()){
            this.status = StatusEmprestimo.ATRASADO;
        }
    }

    @Override
    public int hashCode() {
        return (id == null ? 0 : id.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Emprestimo other)) return false;
        return id != null && id.equals(other.id);
    }

}