package br.com.bernhoeft.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
	Categoria categoria1 = new Categoria();
	Categoria categoria2 = new Categoria();
	CategoriaDTO dto = new CategoriaDTO();

	@BeforeEach
	void setUp() {
		categoria1.setId(1);
		categoria1.setNome("Eletrônicos");
		categoria1.setSituacao(Status.ATIVO);
		categoria2.setId(2);
		categoria2.setNome("Eletrônicos");
		categoria2.setSituacao(Status.ATIVO);
		categorias.add(categoria1);
		categorias.add(categoria2);

		dto.setNome("Eletrônicos");
		dto.setSituacao(Status.ATIVO);
	}

	@Test
	void saveCategoria() {
		Mockito.when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria1);

		Categoria result = service.save(dto);

		Assertions.assertEquals(result.getNome(), dto.getNome());
	}

	@Test
	void updateCategoria() {
		Mockito.when(categoriaRepository.findById(anyInt())).thenReturn(Optional.of(categoria1));
		Mockito.when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria1);

		dto.setId(1);
		dto.setSituacao(Status.INATIVO);
		Categoria result = service.update(dto);

		Assertions.assertEquals(result.getSituacao(), dto.toCategoria().getSituacao());
	}

	@Test
	void updateCategoriaNotExist() {
		Mockito.when(categoriaRepository.findById(anyInt())).thenThrow(EntityNotFoundException.class);

		dto.setId(1);
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.update(dto);
		});
	}

	@Test
	void findAll() {
		Mockito.when(categoriaRepository.findAll()).thenReturn(categorias);

		List<Categoria> result = service.findAll();

		Mockito.verify(categoriaRepository, times(1)).findAll();
		Assertions.assertEquals(2, result.size());
	}

	@Test
	void getAllWithPagination() {
		Page<Categoria> categoriaPage = new PageImpl<>(categorias);

		Mockito.when(categoriaRepository.findAll(any(PageRequest.class))).thenReturn(categoriaPage);

		List<Categoria> result = service.getAllWithPagination(0, 10);

		Mockito.verify(categoriaRepository, times(1)).findAll(any(PageRequest.class));
		Assertions.assertEquals(2, result.size());
	}

	@Test
	void filterCategoriesByName() {
		categorias = new ArrayList<>();
		categorias.add(categoria1);
		Page<Categoria> categoriaPage = new PageImpl<>(categorias);

		Mockito.when(categoriaRepository.findByNomeContaining(anyString(), any(PageRequest.class)))
				.thenReturn(categoriaPage);

		List<Categoria> result = service.filterCategoriesByName("Eletrônicos", 0, 10);

		Mockito.verify(categoriaRepository, times(1)).findByNomeContaining(anyString(), any(PageRequest.class));
		Assertions.assertEquals(1, result.size());
	}

	@Test
	void filterCategoriesBySituation() {
		Page<Categoria> categoriaPage = new PageImpl<>(categorias);

		Mockito.when(categoriaRepository.findBySituacaoContaining(any(Status.class), any(PageRequest.class)))
				.thenReturn(categoriaPage);

		List<Categoria> result = service.filterCategoriesBySituation(Status.ATIVO, 0, 10);

		Mockito.verify(categoriaRepository, times(1)).findBySituacaoContaining(any(Status.class),
				any(PageRequest.class));
		Assertions.assertEquals(2, result.size());
	}

	@Test
	void delete() {
		Mockito.when(categoriaRepository.findById(anyInt())).thenReturn(Optional.of(categoria1));
		Mockito.doNothing().when(categoriaRepository).delete(any(Categoria.class));

		service.delete(categoria1);
	}

}
