package com.uel.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uel.biblioteca.model.Titulo;

@Repository
public interface TituloDAO extends JpaRepository<Titulo, Long> {

}
