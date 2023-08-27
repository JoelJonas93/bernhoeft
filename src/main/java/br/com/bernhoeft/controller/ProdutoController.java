package br.com.bernhoeft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.dto.ProdutoDTO;
import br.com.bernhoeft.model.Produto;
import br.com.bernhoeft.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping("/save")
	public ResponseEntity<Produto> save(@RequestBody ProdutoDTO dto) {
		Produto produto = service.save(dto);
		return new ResponseEntity<>(produto, HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<Produto> update(@RequestBody ProdutoDTO dto) {
		Produto produto = service.update(dto);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@GetMapping("/findall")
	public ResponseEntity<List<Produto>> findaAll() {
		List<Produto> list = service.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/findallpageable")
	public ResponseEntity<Page<Produto>> findAllPageable(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Produto> produtos = service.getAllWithPagination(page, size);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	@GetMapping("/filterbydescription")
	public ResponseEntity<Page<Produto>> filterByDescription(@RequestParam(name = "descricao") String descricao,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Produto> produtos = service.filterProductsByDescription(descricao, page, size);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	@GetMapping("/filterbycategoria")
	public ResponseEntity<Page<Produto>> filterByCategoria(@RequestBody CategoriaDTO dto,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Produto> produtos = service.filterProductsByCategoria(dto.toCategoria(), page, size);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	@GetMapping("/filterbysituacao")
	public ResponseEntity<Page<Produto>> filterBySituacao(@RequestParam(name = "situacao") String situacao,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Produto> produtos = service.filterProductsBySituacao(situacao.toUpperCase(), page, size);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody ProdutoDTO dto) {
		service.delete(dto.toProduto());
	}

}
