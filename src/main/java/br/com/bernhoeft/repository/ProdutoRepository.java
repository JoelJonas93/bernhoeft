package br.com.bernhoeft.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bernhoeft.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {
	Optional<Produto> findById(Integer id);
	Page<Produto> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM produto WHERE categoria_id = ?1",
		    countQuery = "SELECT count(*) FROM produto WHERE categoria_id = ?1",
		    nativeQuery = true)
	Page<Produto> findByCategoriaContaining(Integer id, Pageable pageable);
	
	@Query(value = "SELECT * FROM produto WHERE LOWER(descricao) LIKE LOWER(CONCAT('%', ?1,'%'))",
		    countQuery = "SELECT count(*) FROM produto WHERE LOWER(descricao) LIKE LOWER(CONCAT('%', ?1,'%'))",
		    nativeQuery = true)
	Page<Produto> findByDescricaoContaining(String descricao, Pageable pageable);
	
	@Query(value = "SELECT * FROM produto WHERE LOWER(situacao) LIKE LOWER(CONCAT(?1))",
		    countQuery = "SELECT count(*) FROM produto WHERE LOWER(situacao) LIKE LOWER(CONCAT(?1))",
		    nativeQuery = true)
	Page<Produto> findBySituacaoContaining(String situacao, Pageable pageable);
}
