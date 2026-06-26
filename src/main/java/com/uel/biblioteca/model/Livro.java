package com.uel.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    private Long id;

    private int codigo;

    // Autor e Área serão transformados em classes próprias no futuro, igual ao Titulo.
    private String autor;
    private String area;

    private Titulo titulo;

    private boolean exemplarBiblioteca; // Define se é um livro restrito à biblioteca

    public Livro(int codigo) {
        this.codigo = codigo;
    }

    public boolean verificaLivro() {
        // Se for exemplar da biblioteca o controlador não deixa emprestar
        return this.exemplarBiblioteca;
    }

    // Método chamado pela classe Item para calcular a data de devolução
    public int verPrazo() {
        if (this.titulo != null) {
            return this.titulo.getPrazo();
        }
        return 0; // Prazo padrão de segurança
    }
}