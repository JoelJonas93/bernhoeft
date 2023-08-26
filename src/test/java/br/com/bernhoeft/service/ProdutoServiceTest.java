package br.com.bernhoeft.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.model.Produto;
import br.com.bernhoeft.repository.ProdutoRepository;
import br.com.bernhoeft.service.impl.ProdutoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {
	
	@InjectMocks
	private ProdutoServiceImpl service;
	
	@Mock
	private ProdutoRepository produRepository;
	
	Categoria categoria1 = new Categoria();
	
	List<Produto> produtos = new ArrayList<>();
	Produto produto1 = new Produto();
	Produto produto2 = new Produto();
	ProdutoDTO dto = new ProdutoDTO();
	
	@BeforeEach
	void setUp() {
		categoria1.setId(1l);
		categoria1.setNome("Eletrônicos");
		categoria1.setSituacao(Status.ATIVO);
		
		produto1.setId(1l);
		produto1.setNome("Smartphone");
		produto1.setDescricao("Um telefone inteligente");
		produto1.setPreco(999.99);
		produto1.setSituacao(Status.ATIVO);
		produto1.setCategoria(categoria1);
		produto2.setId(2l);
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
		Mockito.when(produRepository.save(any(Produto.class))).thenReturn(produto1);
		
		Produto result = service.save(dto);
		
		Assertions.assertEquals(result.getNome(), dto.getNome());
	}
}
