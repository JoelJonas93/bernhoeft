package br.com.bernhoeft.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;

public interface CategoriaService {
	
	public Categoria save(CategoriaDTO categoriaDTO);
	public Categoria update(CategoriaDTO categoriaDto);
	public List<Categoria> findAll();
	public Page<Categoria> getAllWithPagination(int page, int size);
	public Page<Categoria> filterCategoriesByName(String name, int page, int size);
	public Page<Categoria> filterCategoriesBySituation(String situation, int page, int size);
	public void delete(Categoria categoria);
}
