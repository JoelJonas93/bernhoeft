package br.com.bernhoeft.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bernhoeft.model.Categoria;
import br.com.bernhoeft.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String> {
	Optional<Produto> findById(Integer id);
	Page<Produto> findAll(Pageable pageable);
	Page<Produto> findByCategoriaContaining(Categoria categoria, Pageable pageable);
	Page<Produto> findByDescricaoContaining(String descricao, Pageable pageable);
	Page<Produto> findBySituacaoContaining(String situacao, Pageable pageable);
}
