package br.com.bernhoeft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.repository.CategoriaRepository;
import br.com.bernhoeft.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;

	@Override
	public Categoria save(CategoriaDTO categoriaDTO) {
		return repository.save(categoriaDTO.toCategoria());
	}

}
