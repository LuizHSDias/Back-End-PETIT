package com.cefet.ds_petit.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_estudante")

public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(length = 150, unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(length = 20, unique = true, nullable = false)
    private String telefone;

    @NotBlank
    @Column(length = 100)
    private String curso;

    @NotBlank
    @Column(length = 20, unique = true, nullable = false)
    private String matricula;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(name = "qtd_emprestimos_ativos")
    private int quantidadeEmprestimosAtivos = 0;

    @OneToMany(mappedBy = "estudante", fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Estudante() {

    }

    public Estudante(Long id, String nome, String email, String telefone, String curso, String matricula) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.curso = curso;
        this.matricula = matricula;
        this.ativo = true;
        this.quantidadeEmprestimosAtivos = 0;
    }

    // GETTERS E SETTERS    
            
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getQuantidadeEmprestimosAtivos() {
        return quantidadeEmprestimosAtivos;
    }

    public void setQuantidadeEmprestimosAtivos(int quantidadeEmprestimosAtivos) {
        this.quantidadeEmprestimosAtivos = quantidadeEmprestimosAtivos;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estudante other = (Estudante) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}