package br.com.bernhoeft.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.repository.CategoriaRepository;
import br.com.bernhoeft.service.impl.CategoriaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {
	
	@InjectMocks
	private CategoriaServiceImpl service;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	void saveCategoria() {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setNome("Eletrônicos");
		dto.setSituacao("Ativo");
		
		Categoria categoria = dto.toCategoria();
        
        Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);
        
        Categoria result = service.save(dto);
        
        Assertions.assertEquals(result.getNome(), dto.toCategoria().getNome());
	}

}
