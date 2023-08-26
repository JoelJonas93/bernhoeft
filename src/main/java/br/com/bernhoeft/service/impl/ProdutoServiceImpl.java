package br.com.bernhoeft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.model.Produto;
import br.com.bernhoeft.repository.ProdutoRepository;
import br.com.bernhoeft.service.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	@Override
	public Produto save(ProdutoDTO produtoDTO) {
		return repository.save(produtoDTO.toProduto());
	}

	@Override
	public Produto update(ProdutoDTO produtoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
