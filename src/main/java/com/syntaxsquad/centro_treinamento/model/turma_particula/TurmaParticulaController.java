package com.syntaxsquad.centro_treinamento.model.turma_particula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turma-particulas")
public class TurmaParticulaController {

    @Autowired
    private TurmaParticulaService turmaParticulaService;

    // CREATE
    @PostMapping
    public ResponseEntity<TurmaParticulaResponse> createTurmaParticula(@RequestBody TurmaParticulaRequest request) {
        TurmaParticula turmaParticula = turmaParticulaService.createTurmaParticula(request);
        TurmaParticulaResponse response = new TurmaParticulaResponse(
                turmaParticula.getId(),
                turmaParticula.getTrainer().getCpf(),
                turmaParticula.getAluno().getCpf()
        );
        return ResponseEntity.ok(response);
    }

    // READ - Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<TurmaParticulaResponse> getTurmaParticulaById(@PathVariable UUID id) {
        TurmaParticula turmaParticula = turmaParticulaService.findById(id);
        if (turmaParticula == null) {
            return ResponseEntity.notFound().build();
        }
        TurmaParticulaResponse response = new TurmaParticulaResponse(
                turmaParticula.getId(),
                turmaParticula.getTrainer().getCpf(),
                turmaParticula.getAluno().getCpf()
        );
        return ResponseEntity.ok(response);
    }

    // READ - Get all
    @GetMapping
    public ResponseEntity<List<TurmaParticulaResponse>> getAllTurmaParticulas() {
        List<TurmaParticula> turmaParticulas = turmaParticulaService.findAll();
        List<TurmaParticulaResponse> responses = turmaParticulas.stream()
                .map(turma -> new TurmaParticulaResponse(
                        turma.getId(),
                        turma.getTrainer().getCpf(),
                        turma.getAluno().getCpf()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TurmaParticulaResponse> updateTurmaParticula(
            @PathVariable UUID id,
            @RequestBody TurmaParticulaRequest request) {
        TurmaParticula updatedTurmaParticula = turmaParticulaService.updateTurmaParticula(id, request);
        if (updatedTurmaParticula == null) {
            return ResponseEntity.notFound().build();
        }
        TurmaParticulaResponse response = new TurmaParticulaResponse(
                updatedTurmaParticula.getId(),
                updatedTurmaParticula.getTrainer().getCpf(),
                updatedTurmaParticula.getAluno().getCpf()
        );
        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurmaParticula(@PathVariable UUID id) {
        boolean deleted = turmaParticulaService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
