package br.com.bernhoeft.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.model.Produto;

public interface ProdutoService {
	
	public Produto save(ProdutoDTO produtoDTO);
	public Produto update(ProdutoDTO produtoDTO);
	public List<Produto> findAll();
	public Page<Produto> getAllWithPagination(int page, int size);
	public Page<Produto> filterProductsByDescription(String description, int page, int size);
	public Page<Produto> filterProductsByCategoria(Categoria categoria, int page, int size);
	public Page<Produto> filterProductsBySituacao(String situacao, int page, int size);
	public void delete(Produto produto);
}
