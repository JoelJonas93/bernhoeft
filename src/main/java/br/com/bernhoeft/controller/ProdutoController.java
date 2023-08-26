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

}
