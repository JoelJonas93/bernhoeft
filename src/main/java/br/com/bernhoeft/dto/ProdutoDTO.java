package br.com.bernhoeft.dto;

import java.util.Date;

import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.model.Produto;
import lombok.Data;

@Data
public class ProdutoDTO {
	
	private Integer id;
	private String nome;
	private String descricao;
	private double preco;
    private Status situacao;
    private Date criadoEm;
    private Categoria categoria;
    
    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setSituacao(situacao);
        produto.setCriadoEm(criadoEm);
        produto.setCategoria(categoria);
        return produto;
    }

}
