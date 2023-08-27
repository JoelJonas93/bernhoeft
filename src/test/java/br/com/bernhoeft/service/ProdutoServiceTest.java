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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.model.Produto;
import br.com.bernhoeft.repository.CategoriaRepository;
import br.com.bernhoeft.repository.ProdutoRepository;
import br.com.bernhoeft.service.impl.ProdutoServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProdutoServiceTest {

	@InjectMocks
	private ProdutoServiceImpl service;

	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private CategoriaRepository categoriaRepository;

	Categoria categoria1 = new Categoria();

	List<Produto> produtos = new ArrayList<>();
	Produto produto1 = new Produto();
	Produto produto2 = new Produto();
	ProdutoDTO dto = new ProdutoDTO();

	@BeforeEach
	void setUp() {
		categoria1.setId(1);
		categoria1.setNome("Eletrônicos");
		categoria1.setSituacao(Status.ATIVO);

		produto1.setId(1);
		produto1.setNome("Smartphone");
		produto1.setDescricao("Um telefone inteligente");
		produto1.setPreco(999.99);
		produto1.setSituacao(Status.ATIVO);
		produto1.setCategoria(categoria1);
		produto2.setId(2);
		produto2.setNome("Tablet");
		produto2.setDescricao("Um dispositivo tablet");
		produto2.setPreco(399.99);
		produto2.setSituacao(Status.ATIVO);
		produto2.setCategoria(categoria1);
		produtos.add(produto1);
		produtos.add(produto2);

		dto.setNome("Smartphone");
		dto.setDescricao("Um telefone inteligente");
		dto.setPreco(999.99);
		dto.setSituacao(Status.ATIVO);
		dto.setCategoria(categoria1);
	}

	@Test
	void saveProduto() {
		Mockito.when(produtoRepository.save(any(Produto.class))).thenReturn(produto1);

		Produto result = service.save(dto);

		Assertions.assertEquals(result.getNome(), dto.getNome());
	}

	@Test
	void updateProduto() {
		Mockito.when(produtoRepository.findById(anyInt())).thenReturn(Optional.of(produto1));
		Mockito.when(produtoRepository.save(any(Produto.class))).thenReturn(produto1);

		dto.setId(1);
		dto.setSituacao(Status.INATIVO);
		Produto result = service.update(dto);

		Assertions.assertEquals(result.getSituacao(), dto.getSituacao());
	}

	@Test
	void updateProdutoNotExist() {
		Mockito.when(produtoRepository.findById(anyInt())).thenThrow(EntityNotFoundException.class);

		dto.setId(1);
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.update(dto);
		});
	}

	@Test
	void findAll() {
		Mockito.when(produtoRepository.findAll()).thenReturn(produtos);

		List<Produto> result = service.findAll();

		Mockito.verify(produtoRepository, times(1)).findAll();
		Assertions.assertEquals(2, result.size());
	}

	@Test
	void getAllWithPagination() {
		Page<Produto> produtoPage = new PageImpl<>(produtos);

		Mockito.when(produtoRepository.findAll(any(PageRequest.class))).thenReturn(produtoPage);

		List<Produto> result = service.getAllWithPagination(0, 10).getContent();

		Mockito.verify(produtoRepository, times(1)).findAll(any(PageRequest.class));
		Assertions.assertEquals(2, result.size());
	}

	@Test
	void filterProductsByDescription() {
		produtos = new ArrayList<>();
		produtos.add(produto1);
		Page<Produto> produtoPage = new PageImpl<>(produtos);

		Mockito.when(produtoRepository.findByDescricaoContaining(anyString(), any(PageRequest.class))).thenReturn(produtoPage);

		List<Produto> result = service.filterProductsByDescription("", 0, 10).getContent();

		Mockito.verify(produtoRepository, times(1)).findByDescricaoContaining(anyString(), any(PageRequest.class));
		Assertions.assertEquals(1, result.size());
	}
	
	@Test
	void filterProductsByCategoria() {
		produtos = new ArrayList<>();
		produtos.add(produto1);
		Page<Produto> produtoPage = new PageImpl<>(produtos);
		
		Mockito.when(categoriaRepository.findById(anyInt())).thenReturn(Optional.of(categoria1));
		Mockito.when(produtoRepository.findByCategoriaContaining(anyInt(), any(PageRequest.class))).thenReturn(produtoPage);

		List<Produto> result = service.filterProductsByCategoria(categoria1 , 0, 10).getContent();

		Mockito.verify(produtoRepository, times(1)).findByCategoriaContaining(anyInt(), any(PageRequest.class));
		Assertions.assertEquals(1, result.size());
	}
	
	@Test
	void filterProductsBySituacao() {
		produtos = new ArrayList<>();
		produtos.add(produto1);
		Page<Produto> produtoPage = new PageImpl<>(produtos);

		Mockito.when(produtoRepository.findBySituacaoContaining(anyString(), any(PageRequest.class))).thenReturn(produtoPage);

		List<Produto> result = service.filterProductsBySituacao("", 0, 10).getContent();

		Mockito.verify(produtoRepository, times(1)).findBySituacaoContaining(anyString(), any(PageRequest.class));
		Assertions.assertEquals(1, result.size());
	}
	
	@Test
	void delete() {
		Mockito.when(produtoRepository.findById(anyInt())).thenReturn(Optional.of(produto1));
		Mockito.doNothing().when(produtoRepository).delete(any(Produto.class));
		
		service.delete(produto1);
	}
}
