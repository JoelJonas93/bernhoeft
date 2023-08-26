package br.com.bernhoeft.service;

import java.util.List;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.model.Produto;

public interface ProdutoService {
	
	public Produto save(ProdutoDTO produtoDTO);
	public Produto update(ProdutoDTO produtoDTO);
	public List<Produto> findAll();
	public List<Produto> getAllWithPagination(int page, int size);
}
