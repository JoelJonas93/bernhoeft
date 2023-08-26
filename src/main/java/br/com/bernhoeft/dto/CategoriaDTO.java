package br.com.bernhoeft.dto;

import br.com.bernhoeft.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO {
	
	private Long id;
	private String nome;
    private String situacao;
    
    public Categoria toCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNome(nome);
        categoria.setSituacao(situacao);
        return categoria;
    }

}
