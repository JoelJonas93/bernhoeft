package br.com.bernhoeft.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.repository.CategoriaRepository;
import br.com.bernhoeft.service.impl.CategoriaServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

	@InjectMocks
	private CategoriaServiceImpl service;

	@Mock
	private CategoriaRepository categoriaRepository;

	List<Categoria> categorias = new ArrayList<>();

	@BeforeEach
	void setUp() {
		Categoria categoria1 = new Categoria();
		categoria1.setId(1l);
		categoria1.setNome("Eletrônicos");
		categoria1.setSituacao(Status.ATIVO);
		Categoria catategoria2 = new Categoria();
		catategoria2.setId(1l);
		catategoria2.setNome("Eletrônicos");
		catategoria2.setSituacao(Status.ATIVO);
		categorias.add(categoria1);
		categorias.add(catategoria2);
	}

	@Test
	void saveCategoria() {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setNome("Eletrônicos");
		dto.setSituacao(Status.ATIVO);

		Categoria categoria = dto.toCategoria();

		Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);

		Categoria result = service.save(dto);

		Assertions.assertEquals(result.getNome(), dto.toCategoria().getNome());
	}

	@Test
	void updateCategoria() {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setId(1l);
		dto.setNome("Eletrônicos");
		dto.setSituacao(Status.ATIVO);

		Categoria categoria = dto.toCategoria();

		Mockito.when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoria));
		dto.setSituacao(Status.INATIVO);
		Mockito.when(categoriaRepository.save(categoria)).thenReturn(dto.toCategoria());

		Categoria result = service.update(dto);

		Assertions.assertEquals(result.getSituacao(), dto.toCategoria().getSituacao());
	}

	@Test
	void updateCategoriaNotExist() {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setId(1l);
		;
		dto.setNome("Eletrônicos");
		dto.setSituacao(Status.ATIVO);

		Mockito.when(categoriaRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);

		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.update(dto);
		});
	}

	@Test
	void getAllWithPagination() {
		Page<Categoria> categoriaPage = new PageImpl<>(categorias);

		Mockito.when(categoriaRepository.findAll(any(PageRequest.class))).thenReturn(categoriaPage);

		List<Categoria> result = service.getAllWithPagination(0, 10);

		Mockito.verify(categoriaRepository, times(1)).findAll(any(PageRequest.class));
		Assertions.assertEquals(2, result.size());
	}

}
