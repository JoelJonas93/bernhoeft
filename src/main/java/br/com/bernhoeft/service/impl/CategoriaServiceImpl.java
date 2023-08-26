package br.com.bernhoeft.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.repository.CategoriaRepository;
import br.com.bernhoeft.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;

public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Override
	public Categoria save(CategoriaDTO categoriaDTO) {
		return repository.save(categoriaDTO.toCategoria());
	}

	@Override
	public Categoria update(CategoriaDTO categoriaDto) {
		Optional<Categoria> categoriaOptional = repository.findById(categoriaDto.getId());
		if (categoriaOptional.isPresent()) {
			Categoria categoria = categoriaOptional.get();
			categoria.setNome(categoriaDto.getNome());
			categoria.setSituacao(categoriaDto.getSituacao());
			return repository.save(categoria);
		} else {
			throw new EntityNotFoundException("Categoria não encontrada com ID: " + categoriaDto.getId());
		}
	}

	@Override
	public List<Categoria> getAllWithPagination(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Categoria> categoriaPage = repository.findAll(pageRequest);
        return categoriaPage.getContent();
	}

	@Override
	public List<Categoria> filterCategoriesByName(String name, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Categoria> categoriaPage = repository.findByNome(name, pageRequest);
        return categoriaPage.getContent();
	}

}
