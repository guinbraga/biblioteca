package com.uel.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uel.biblioteca.model.Aluno;

@Repository
public interface AlunoDAO extends JpaRepository<Aluno, Long> {
	
	Aluno findByRA(String ra);

}
