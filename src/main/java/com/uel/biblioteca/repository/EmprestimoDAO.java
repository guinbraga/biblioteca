package com.uel.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uel.biblioteca.model.Emprestimo;

@Repository
public interface EmprestimoDAO extends JpaRepository<Emprestimo, Long> {

}
