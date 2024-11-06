package com.syntaxsquad.centro_treinamento.model.turmas;

import com.syntaxsquad.centro_treinamento.model.turmas.Turma;
import com.syntaxsquad.centro_treinamento.model.turmas.TurmaRequest;
import com.syntaxsquad.centro_treinamento.model.turmas.TurmaResponse;
import com.syntaxsquad.centro_treinamento.model.turmas.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> getTurmaById(@PathVariable UUID id) {
        Turma turma = turmaService.findById(id);
        if (turma == null) {
            return ResponseEntity.notFound().build();
        }
        TurmaResponse response = new TurmaResponse(
                turma.getId(),
                turma.getTrainer().getCpf(),
                turma.getTreino().getId(),
                turma.getHorario()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TurmaResponse>> getAllTurmas() {
        List<Turma> turmas = turmaService.findAll();
        List<TurmaResponse> responses = turmas.stream()
                .map(turma -> new TurmaResponse(
                        turma.getId(),
                        turma.getTrainer().getCpf(),
                        turma.getTreino().getId(),
                        turma.getHorario()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<TurmaResponse> createTurma(@RequestBody TurmaRequest turmaRequest) {
        Turma savedTurma = turmaService.save(turmaRequest);
        TurmaResponse response = new TurmaResponse(
                savedTurma.getId(),
                savedTurma.getTrainer().getCpf(),
                savedTurma.getTreino().getId(),
                savedTurma.getHorario()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> updateTurma(@PathVariable UUID id, @RequestBody TurmaRequest turmaRequest) {
        Turma updatedTurma = turmaService.update(id, turmaRequest);
        if (updatedTurma == null) {
            return ResponseEntity.notFound().build();
        }
        TurmaResponse response = new TurmaResponse(
                updatedTurma.getId(),
                updatedTurma.getTrainer().getCpf(),
                updatedTurma.getTreino().getId(),
                updatedTurma.getHorario()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable UUID id) {
        boolean deleted = turmaService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
