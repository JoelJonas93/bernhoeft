package br.com.bernhoeft.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
	@JsonProperty("ATIVO")
	ATIVO("ATIVO"), 
	@JsonProperty("INATIVO")
	INATIVO("INATIVO");

	private final String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
