package br.com.bernhoeft.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bernhoeft.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {
	
	Optional<Categoria> findById(Integer id);
	Page<Categoria> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM categoria WHERE LOWER(nome) LIKE LOWER(CONCAT('%', ?1,'%'))",
		    countQuery = "SELECT count(*) FROM categoria WHERE LOWER(nome) LIKE LOWER(CONCAT('%', ?1,'%'))",
		    nativeQuery = true)
	Page<Categoria> findByNomeContaining(String nome, Pageable pageable);
	
	@Query(value = "SELECT * FROM categoria WHERE LOWER(situacao) LIKE LOWER(CONCAT(?1))",
		    countQuery = "SELECT count(*) FROM categoria WHERE LOWER(situacao) LIKE LOWER(CONCAT(?1))",
		    nativeQuery = true)
	Page<Categoria> findBySituacaoContaining(String situacao, Pageable pageable);
}
