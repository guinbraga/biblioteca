package com.uel.biblioteca.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> item = new ArrayList<>();

    // Construtor auxiliar para facilitar o padrão Creator
    public Emprestimo(Aluno aluno) {
        this.aluno = aluno;
        this.dataEmprestimo = LocalDate.now();
    }

    public boolean emprestar(List<Livro> livros) {
        if (livros == null || livros.isEmpty()) return false;

        for (Livro livro : livros) {
            item.add(new Item(livro));
        }
        
        CalculaDataDevolucao();
        
        return true;
    }

    private void CalculaDataDevolucao() {
        LocalDate dataAtual = LocalDate.now();

        // Calcula o maior prazo individual
        for (Item it : item) {
            LocalDate dataAux = it.calculaDataDevolucao(dataAtual);
            if (this.dataPrevista == null || this.dataPrevista.isBefore(dataAux)) {
                this.dataPrevista = dataAux;
            }
        }
        
        // Aplica a regra de +2 dias para cada livro extra (após o 2º)
        if (item.size() > 2) {
            int tam = item.size() - 2;
            this.dataPrevista = this.dataPrevista.plusDays(tam * 2);
        }
        
        // Atualiza cada item com a data final calculada
        for (Item it : item) {
            it.setDataDevolucao(this.dataPrevista);
        }
    }
}