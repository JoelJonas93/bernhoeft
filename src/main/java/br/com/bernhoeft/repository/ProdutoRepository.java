package br.com.bernhoeft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bernhoeft.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
	Optional<Produto> findById(Long id);
}
