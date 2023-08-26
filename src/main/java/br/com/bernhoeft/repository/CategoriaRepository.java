package br.com.bernhoeft.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bernhoeft.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {
	
	Optional<Categoria> findById(Long id);
	Page<Categoria> findAll(Pageable pageable);	
}
