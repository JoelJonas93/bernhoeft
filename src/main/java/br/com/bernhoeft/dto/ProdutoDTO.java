package br.com.bernhoeft.dto;

import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.model.Produto;
import lombok.Data;

@Data
public class ProdutoDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private double preco;
    private Status situacao;
    private Categoria categoria;
    
    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        return produto;
    }

}
