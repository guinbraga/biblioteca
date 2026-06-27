package com.uel.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uel.biblioteca.model.Livro;
import com.uel.biblioteca.repository.LivroDAO;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroDAO livroDAO;

    @PostMapping
    public ResponseEntity<?> cadastrarLivro(@RequestBody Livro livro) {
        if (livroDAO.findByCodigo(livro.getCodigo()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um livro com o código " + livro.getCodigo() + "!");
        }
        Livro livroSalvo = livroDAO.save(livro);
        return ResponseEntity.ok(livroSalvo);
    }
    
    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroDAO.findAllComTitulo();
        return ResponseEntity.ok(livros);
    }
    
    @GetMapping("/buscar-por-titulo")
    public ResponseEntity<List<Livro>> buscarLivroPorNomeDoTitulo(@RequestParam("nome") String nomeTitulo) {
        
        List<Livro> livros = livroDAO.findByTituloNomeContainingIgnoreCase(nomeTitulo);
        
        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroDAO.findById(id);
        return livro.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        return livroDAO.findById(id).map(livroExistente -> {
            // Atualiza os atributos da instância gerenciada pelo banco
            livroExistente.setCodigo(livroAtualizado.getCodigo());
            livroExistente.setAutor(livroAtualizado.getAutor());
            livroExistente.setArea(livroAtualizado.getArea());
            livroExistente.setExemplarBiblioteca(livroAtualizado.isExemplarBiblioteca());
            
            // Salva as alterações
            Livro livroSalvo = livroDAO.save(livroExistente);
            return ResponseEntity.ok(livroSalvo);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        if (livroDAO.existsById(id)) {
            livroDAO.deleteById(id);
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.notFound().build(); 
    }
}