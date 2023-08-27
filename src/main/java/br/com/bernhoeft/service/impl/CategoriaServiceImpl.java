package br.com.bernhoeft.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.repository.CategoriaRepository;
import br.com.bernhoeft.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Override
	public Categoria save(CategoriaDTO categoriaDTO) {
		categoriaDTO.setCriadoEm(new Date());
		return repository.save(categoriaDTO.toCategoria());
	}

	@Override
	public Categoria update(CategoriaDTO categoriaDto) {
		if(categoriaDto.getId() == null) throw new EntityNotFoundException("Id não pode ser nulo");
		Optional<Categoria> categoriaOptional = repository.findById(categoriaDto.getId());
		if (categoriaOptional.isPresent()) {
			Categoria categoria = categoriaOptional.get();
			categoria.setAtualizadoEm(new Date());
			categoria.setNome(categoriaDto.getNome());
			categoria.setSituacao(categoriaDto.getSituacao());
			return repository.save(categoria);
		} else {
			throw new EntityNotFoundException("Categoria não encontrada com ID: " + categoriaDto.getId());
		}
	}
	
	@Override
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Categoria> getAllWithPagination(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Categoria> categoriaPage = repository.findAll(pageRequest);
        return categoriaPage;
	}

	@Override
	public Page<Categoria> filterCategoriesByName(String name, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Categoria> categoriaPage = repository.findByNomeContaining(name, pageRequest);
        return categoriaPage;
	}

	@Override
	public Page<Categoria> filterCategoriesBySituation(String situation, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
        Page<Categoria> categoriaPage = repository.findBySituacaoContaining(situation, pageRequest);
        return categoriaPage;
	}

	@Override
	public void delete(Categoria categoria) {
		Optional<Categoria> categoriaOptional = repository.findById(categoria.getId());
		if(categoriaOptional.isPresent()) {
			repository.delete(categoria);
		}
	}
}
