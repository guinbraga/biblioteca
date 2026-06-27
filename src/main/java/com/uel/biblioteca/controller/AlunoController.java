package com.uel.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uel.biblioteca.model.Aluno;
import com.uel.biblioteca.repository.AlunoDAO;

@RestController
@RequestMapping("/api/alunos") // rota API - independe do desenvolvimento da view
public class AlunoController {

    @Autowired
    private AlunoDAO alunoDAO;

    @PostMapping
    public ResponseEntity<?> cadastrarAluno(@RequestBody Aluno aluno) {

        // validação na Model
        if (!aluno.verficaAluno()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha na validação: RA inválido ou nulo!");
        }

        if (alunoDAO.findByRA(aluno.getRA()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Já existe um aluno cadastrado com o RA " + aluno.getRA() + "!");
        }

        Aluno alunoSalvo = alunoDAO.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoDAO.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/buscar-por-ra")
    public ResponseEntity<Aluno> buscarAlunoPorRa(@RequestParam("ra") String ra) {
        Aluno aluno = alunoDAO.findByRA(ra);
        if (aluno == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(aluno);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoDAO.findById(id);
        return aluno.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Optional<Aluno> existenteOpt = alunoDAO.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // validação na Model
        if (!alunoAtualizado.verficaAluno()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha na validação: RA inválido ou nulo!");
        }

        Aluno outro = alunoDAO.findByRA(alunoAtualizado.getRA());
        if (outro != null && !outro.getId().equals(id)) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Já existe outro aluno com o RA " + alunoAtualizado.getRA() + "!");
        }

        Aluno alunoExistente = existenteOpt.get();
        alunoExistente.setRA(alunoAtualizado.getRA());
        alunoExistente.setNome(alunoAtualizado.getNome());

        Aluno alunoSalvo = alunoDAO.save(alunoExistente);
        return ResponseEntity.ok(alunoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        if (alunoDAO.existsById(id)) {
            alunoDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}