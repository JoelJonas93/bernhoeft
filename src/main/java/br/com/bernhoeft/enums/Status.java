package br.com.bernhoeft.enums;

public enum Status {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
