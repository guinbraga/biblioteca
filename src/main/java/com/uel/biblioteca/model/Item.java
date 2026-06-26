package com.uel.biblioteca.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;
    
    private LocalDate dataDevolucao;

    // Quando um item e criado, um livro e associado a ele
    public Item(Livro livro) {
        this.livro = livro;
    }

    // Metodo para calcular a data de Devolucao de cada Item
    public LocalDate calculaDataDevolucao(LocalDate data) {
        this.dataDevolucao = data.plusDays(livro.verPrazo());
        return this.dataDevolucao;
    }
}