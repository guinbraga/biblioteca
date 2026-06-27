package com.uel.biblioteca.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String RA;
    
    private String nome;
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean verficaAluno() {   
        // Se o RA for null retorna erro - metodo aleatorio
        if (this.RA == null) {
            return false;
        }
        if (this.RA.equals("10")) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean verificaDebito() {
        // Padrão Expert: O aluno possui a informação do seu RA e verifica seu débito
        Debito debito = new Debito(Integer.parseInt(this.RA));
        return debito.verificaDebito(); 
    }

    // Padrão Creator: O Aluno é o responsável por criar (instanciar) o Empréstimo
    public Emprestimo emprestar(List<Livro> livros) {
        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setAluno(this); 
        novoEmprestimo.emprestar(livros);
        return novoEmprestimo;
    }
}