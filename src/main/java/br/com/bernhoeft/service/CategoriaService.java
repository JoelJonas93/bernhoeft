package br.com.bernhoeft.service;

import java.util.List;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;

public interface CategoriaService {
	
	public Categoria save(CategoriaDTO categoriaDTO);
	public Categoria update(CategoriaDTO categoriaDto);
	public List<Categoria> getAllWithPagination(int page, int size);

}
