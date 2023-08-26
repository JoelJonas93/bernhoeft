package br.com.bernhoeft.service;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.model.Produto;

public interface ProdutoService {
	
	public Produto save(ProdutoDTO produtoDTO);
}
