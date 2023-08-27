package br.com.bernhoeft.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.model.Produto;
import br.com.bernhoeft.repository.CategoriaRepository;
import br.com.bernhoeft.repository.ProdutoRepository;
import br.com.bernhoeft.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Produto save(ProdutoDTO produtoDTO) {
		produtoDTO.setCriadoEm(new Date());
		return repository.save(produtoDTO.toProduto());
	}

	@Override
	public Produto update(ProdutoDTO produtoDTO) {
		if(produtoDTO.getId() == null) throw new EntityNotFoundException("Id não pode ser nulo");
		Optional<Produto> produtoOptional = repository.findById(produtoDTO.getId());
		if (produtoOptional.isPresent()) {
			Produto produto = produtoOptional.get();
			produto.setNome(produtoDTO.getNome());
			produto.setDescricao(produtoDTO.getDescricao());
			produto.setPreco(produtoDTO.getPreco());
			produto.setSituacao(produtoDTO.getSituacao());
			produto.setAtualizadoEm(new Date());
			produto.setCategoria(produtoDTO.getCategoria());
			return repository.save(produto);
		} else {
			throw new EntityNotFoundException("Produto não encontrada com ID: " + produtoDTO.getId());
		}
	}

	@Override
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Page<Produto> getAllWithPagination(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Produto> produtoPage = repository.findAll(pageRequest);
        return produtoPage;
	}

	public Page<Produto> filterProductsByDescription(String description, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Produto> produtoPage = repository.findByDescricaoContaining(description, pageRequest);
        return produtoPage;
	}

	public Page<Produto> filterProductsByCategoria(Categoria categoria, int page, int size) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoria.getId());
		if(categoriaOptional.isPresent()) {
			PageRequest pageRequest = PageRequest.of(page, size);
			Page<Produto> produtoPage = repository.findByCategoriaContaining(categoriaOptional.get(), pageRequest);
			return produtoPage;
		} else {
			throw new EntityNotFoundException("Categoria não encontrada");
		}
	}

	public Page<Produto> filterProductsBySituacao(String situacao, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Produto> produtoPage = repository.findBySituacaoContaining(situacao, pageRequest);
        return produtoPage;
	}

	@Override
	public void delete(Produto produto) {
		Optional<Produto> produtoOptional = repository.findById(produto.getId());
		if(produtoOptional.isPresent()) {
			repository.delete(produto);
		}
	}
}
