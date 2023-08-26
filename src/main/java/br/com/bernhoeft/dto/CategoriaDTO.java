package br.com.bernhoeft.dto;

import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO {
	
	private Long id;
	private String nome;
    private Status situacao;
    
    public Categoria toCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNome(nome);
        categoria.setSituacao(situacao);
        return categoria;
    }

}
