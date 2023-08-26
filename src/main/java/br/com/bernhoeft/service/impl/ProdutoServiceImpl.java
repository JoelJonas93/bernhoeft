package br.com.bernhoeft.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

	@Override
	public List<Produto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Produto> getAllWithPagination(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Produto> produtoPage = repository.findAll(pageRequest);
        return produtoPage.getContent();
	}
}
