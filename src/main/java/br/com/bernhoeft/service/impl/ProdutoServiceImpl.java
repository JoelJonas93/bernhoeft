package br.com.bernhoeft.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.model.Produto;
import br.com.bernhoeft.repository.ProdutoRepository;
import br.com.bernhoeft.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;

public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	@Override
	public Produto save(ProdutoDTO produtoDTO) {
		return repository.save(produtoDTO.toProduto());
	}

	@Override
	public Produto update(ProdutoDTO produtoDTO) {
		Optional<Produto> produtoOptional = repository.findById(produtoDTO.getId());
		if (produtoOptional.isPresent()) {
			Produto produto = produtoOptional.get();
			produto.setNome(produtoDTO.getNome());
			produto.setDescricao(produtoDTO.getDescricao());
			produto.setPreco(produtoDTO.getPreco());
			produto.setSituacao(produtoDTO.getSituacao());
			produto.setCategoria(produtoDTO.getCategoria());
			return repository.save(produto);
		} else {
			throw new EntityNotFoundException("Produto não encontrada com ID: " + produtoDTO.getId());
		}
	}

}
