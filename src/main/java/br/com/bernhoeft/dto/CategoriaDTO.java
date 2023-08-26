package br.com.bernhoeft.dto;

import br.com.bernhoeft.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO {
	
	private String nome;
    private String situacao;
    
    public Categoria toCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setSituacao(situacao);
        return categoria;
    }

}
