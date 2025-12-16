package com.cefet.ds_petit.entities;

public enum StatusEmprestimo {
    ATIVO("Ativo"),
    DEVOLVIDO("Devolvido"),
    ATRASADO("Atrasado");

    private final String descricao;

    StatusEmprestimo(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    @Override
    public String toString(){
        return descricao;
    }

    
}