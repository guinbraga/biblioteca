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
    
    public String getNome() {
        return RA;
    }
    
    public void setNome(String nome) {
        this.RA = nome;
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
        // Instancia um objeto debito
        Debito debito = new Debito(Integer.parseInt(this.RA)); 
        /* Aqui voce deve chamar o metodo verificaDebito da classe debito */
        return false;
    }
    
    // Metodo que delega a funcionalidade de emprestar para a classe emprestimo
    public boolean emprestar(List<Livro> livros) {
        /* Aqui voce deve instanciar um objeto emprestimo */
        /* Aqui voce deve chamar o metodo emprestar da classe emprestimo */
        
        return false;
    }
}