package br.com.bernhoeft.dto;

import java.util.Date;

import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO {
	
	private Integer id;
	private String nome;
    private Status situacao;
    private Date criadoEm;
    
    public Categoria toCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNome(nome);
        categoria.setSituacao(situacao);
        categoria.setCriadoEm(criadoEm);
        return categoria;
    }

}
