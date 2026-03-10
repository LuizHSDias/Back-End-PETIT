package com.cefet.ds_petit.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_petiano")
public class Petiano {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_entrada" , nullable = false)
    private LocalDate dataEntrada;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_estudante", nullable = false, unique = true)
    private Estudante estudante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPetiano tipo;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String login;

    @NotBlank
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acesso", nullable = false)
    private NivelAcesso nivelAcesso;

    @Column(length = 300)
    private String urlImagem;


    public Petiano(){

    }

    public Petiano(Long id, LocalDate dataEntrada, Estudante estudante, TipoPetiano tipo, String login, String senha, NivelAcesso nivelAcesso, String urlImagem) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.estudante = estudante;
        this.tipo = tipo;
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
        this.urlImagem = urlImagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public TipoPetiano getTipo(){
        return tipo;
    }

    public void setTipo(TipoPetiano tipo){
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
        result = prime * result + ((estudante == null) ? 0 : estudante.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        result = prime * result + ((nivelAcesso == null) ? 0 : nivelAcesso.hashCode());
        result = prime * result + ((urlImagem == null) ? 0 : urlImagem.hashCode());
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
        Petiano other = (Petiano) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (dataEntrada == null) {
            if (other.dataEntrada != null)
                return false;
        } else if (!dataEntrada.equals(other.dataEntrada))
            return false;
        if (estudante == null) {
            if (other.estudante != null)
                return false;
        } else if (!estudante.equals(other.estudante))
            return false;
        if (tipo != other.tipo)
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        if (nivelAcesso != other.nivelAcesso)
            return false;
        if (urlImagem == null) {
            if (other.urlImagem != null)
                return false;
        } else if (!urlImagem.equals(other.urlImagem))
            return false;
        return true;
    }  
}