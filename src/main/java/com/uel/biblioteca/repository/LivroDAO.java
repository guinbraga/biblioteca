package com.uel.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uel.biblioteca.model.Livro;

@Repository
public interface LivroDAO extends JpaRepository<Livro, Long> {
	
	Livro findByCodigo(int codigo);
	
	List<Livro> findByTituloNomeContainingIgnoreCase(String nome);

}
