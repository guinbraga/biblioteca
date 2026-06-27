package com.uel.biblioteca.controller;

import com.uel.biblioteca.model.Aluno;
import com.uel.biblioteca.repository.AlunoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alunos") // rota API - independer do desenvolvimento da view
public class AlunoController {

    @Autowired
    private AlunoDAO alunoDAO;

    @PostMapping
    public ResponseEntity<?> cadastrarAluno(@RequestBody Aluno aluno) {
        
        // validação na Model
        if (!aluno.verficaAluno()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha na validação: RA inválido ou nulo.");
        }
        
        Aluno alunoSalvo = alunoDAO.save(aluno);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }
}