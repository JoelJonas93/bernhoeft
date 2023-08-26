package br.com.bernhoeft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bernhoeft.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {

}
