package com.uel.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int codigo;

    // Autor e Área serão transformados em classes próprias no futuro, igual ao Titulo.
    private String autor;
    private String area;

    @ManyToOne
    @JoinColumn(name = "titulo_id")
    private Titulo titulo;

    @Column(name = "exemplar_biblioteca")
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