package br.com.bernhoeft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bernhoeft.dto.CategoriaDTO;
import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/save")
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

}
