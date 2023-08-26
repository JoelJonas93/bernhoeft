package br.com.bernhoeft.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bernhoeft.enums.Status;
import br.com.bernhoeft.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {
	
	Optional<Categoria> findById(Integer id);
	Page<Categoria> findAll(Pageable pageable);
	Page<Categoria> findByNome(String nome, Pageable pageable);
	Page<Categoria> findBySituacao(Status situacao, Pageable pageable);
}
