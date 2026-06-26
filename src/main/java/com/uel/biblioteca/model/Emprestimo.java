package com.uel.biblioteca.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;

    private LocalDate dataPrevista;

    @Transient
    private LocalDate data_aux;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> item = new ArrayList<>();

    private int emprestimo = 0;

    public boolean emprestar(List<Livro> livros) {
        for (int i = 0; i < livros.size(); i++) {
            item.add(new Item(livros.get(i)));
        }

        CalculaDataDevolucao();
        System.out.print("\nNumero de Livros Emprestados: " + this.emprestimo);
        System.out.print("\nData de Emprestimo: " + this.dataEmprestimo);
        System.out.print("\nData de Devolucao: " + this.dataPrevista);
        return true;
    }

    private LocalDate CalculaDataDevolucao() {
        // Pega a data atual do sistema
        LocalDate dataAtual = LocalDate.now();

        for (int j = 0; j < item.size(); j++) {
            
        	this.data_aux = item.get(j).calculaDataDevolucao(dataAtual);
            
            if (this.dataPrevista == null || this.dataPrevista.isBefore(data_aux)) {
            	this.dataPrevista = this.data_aux;
            }
        }
        
        if (item.size() > 2) {
            int tam = item.size() - 2;
            // O plusDays() substitui todas aquelas 4 linhas do Calendar
            this.dataPrevista = this.dataPrevista.plusDays(tam * 2);
        }
        
        for (int j = 0; j < item.size(); j++) {
            item.get(j).setDataDevolucao(this.dataPrevista);
        }

        return this.dataPrevista;
    }
}