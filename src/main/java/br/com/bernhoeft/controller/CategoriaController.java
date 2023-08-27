package br.com.bernhoeft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@PostMapping("/save")
	public ResponseEntity<Categoria> save(@RequestBody CategoriaDTO dto) {
		Categoria categoria = service.save(dto);
		return new ResponseEntity<>(categoria, HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Categoria> update(@RequestBody CategoriaDTO dto) {
		Categoria categoria = service.update(dto);
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}
	
	@GetMapping("/findall")
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> list = service.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/findallpageable")
	public ResponseEntity<Page<Categoria>> findAllPageable(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Categoria> categorias = service.getAllWithPagination(page, size);
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}
	
	@GetMapping("/filterbynome")
	public ResponseEntity<Page<Categoria>> filterByNome(@RequestParam(name = "nome") String nome,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Categoria> categorias = service.filterCategoriesByName(nome, page, size);
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}
	
	@GetMapping("/filterbysituacao")
	public ResponseEntity<Page<Categoria>> filterBySituacao(@RequestParam(name = "situacao") String situacao,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<Categoria> categorias = service.filterCategoriesBySituation(situacao, page, size);
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody CategoriaDTO dto) {
		service.delete(dto.toCategoria());
	}

}
