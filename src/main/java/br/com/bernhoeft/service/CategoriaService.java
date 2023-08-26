package br.com.bernhoeft.service;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;

public interface CategoriaService {
	
	public Categoria save(CategoriaDTO categoriaDTO);

}
