package com.uel.biblioteca.controller;

import com.uel.biblioteca.model.Aluno;
import com.uel.biblioteca.model.Emprestimo;
import com.uel.biblioteca.model.Livro;
import com.uel.biblioteca.repository.AlunoDAO;
import com.uel.biblioteca.repository.EmprestimoDAO;
import com.uel.biblioteca.repository.LivroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private AlunoDAO alunoDAO;

    @Autowired
    private LivroDAO livroDAO;

    @Autowired
    private EmprestimoDAO emprestimoDAO;

    @PostMapping
    public ResponseEntity<?> realizarEmprestimo(@RequestBody EmprestimoRequest request) {
        
        Aluno aluno = alunoDAO.findByRA(request.getAlunoRa());
        if (aluno == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
        }

        // Aplicação do padrão Expert: Aluno sabe se tem débito ou impedimento
        if (!aluno.verificaDebito()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Aluno possui débitos ou restrições.");
        }

        List<Livro> livrosParaEmprestar = new ArrayList<>();
        
        //  Busca e valida cada livro solicitado
        for (int codigo : request.getCodigosLivros()) {
            Livro livro = livroDAO.findByCodigo(codigo);
            if (livro == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro com código " + codigo + " não encontrado.");
            }
            
            // Aplicação do padrão Expert: O próprio livro sabe se pode ser emprestado
            if (livro.verificaLivro()) { 
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O livro " + codigo + " é exemplar restrito.");
            }
            
            livrosParaEmprestar.add(livro);
        }

        // Aplicação do padrão Creator: Instancia o Emprestimo associando ao Aluno
        Emprestimo emprestimo = new Emprestimo(aluno);
        
        // O próprio objeto Emprestimo calcula prazos e gera seus itens internos
        emprestimo.emprestar(livrosParaEmprestar);

        // Persiste o caso de uso concluído no banco
        Emprestimo emprestimoSalvo = emprestimoDAO.save(emprestimo);

        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoSalvo);
    }
}

// DTO para receber os dados do JSON
class EmprestimoRequest {
    private String alunoRa;
    private List<Integer> codigosLivros;

    public String getAlunoRa() { return alunoRa; }
    public void setAlunoRa(String alunoRa) { this.alunoRa = alunoRa; }
    public List<Integer> getCodigosLivros() { return codigosLivros; }
    public void setCodigosLivros(List<Integer> codigosLivros) { this.codigosLivros = codigosLivros; }
}