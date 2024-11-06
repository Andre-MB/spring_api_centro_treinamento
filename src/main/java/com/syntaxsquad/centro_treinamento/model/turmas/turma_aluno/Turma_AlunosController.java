package com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno;

import com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno.Turma_Alunos;
import com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno.Turma_AlunosRequest;
import com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno.Turma_AlunosResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turma-alunos")
public class Turma_AlunosController {

    @Autowired
    private Turma_AlunosService turmaAlunosService;

    @GetMapping("/{id}")
    public ResponseEntity<Turma_AlunosResponse> getTurma_AlunosById(@PathVariable UUID id) {
        Turma_Alunos turmaAlunos = turmaAlunosService.findById(id);
        if (turmaAlunos == null) {
            return ResponseEntity.notFound().build();
        }
        Turma_AlunosResponse response = new Turma_AlunosResponse(
                turmaAlunos.getId(),
                turmaAlunos.getTurma().getId(),
                turmaAlunos.getAluno().getCpf()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Turma_AlunosResponse>> getAllTurma_Alunos() {
        List<Turma_Alunos> turmaAlunosList = turmaAlunosService.findAll();
        List<Turma_AlunosResponse> responses = turmaAlunosList.stream()
                .map(turmaAlunos -> new Turma_AlunosResponse(
                        turmaAlunos.getId(),
                        turmaAlunos.getTurma().getId(),
                        turmaAlunos.getAluno().getCpf()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Turma_AlunosResponse> createTurma_Alunos(@RequestBody Turma_AlunosRequest turmaAlunosRequest) {
        Turma_Alunos savedTurmaAlunos = turmaAlunosService.save(turmaAlunosRequest);
        Turma_AlunosResponse response = new Turma_AlunosResponse(
                savedTurmaAlunos.getId(),
                savedTurmaAlunos.getTurma().getId(),
                savedTurmaAlunos.getAluno().getCpf()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma_AlunosResponse> updateTurma_Alunos(@PathVariable UUID id, @RequestBody Turma_AlunosRequest turmaAlunosRequest) {
        Turma_Alunos updatedTurmaAlunos = turmaAlunosService.update(id, turmaAlunosRequest);
        if (updatedTurmaAlunos == null) {
            return ResponseEntity.notFound().build();
        }
        Turma_AlunosResponse response = new Turma_AlunosResponse(
                updatedTurmaAlunos.getId(),
                updatedTurmaAlunos.getTurma().getId(),
                updatedTurmaAlunos.getAluno().getCpf()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma_Alunos(@PathVariable UUID id) {
        boolean deleted = turmaAlunosService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
