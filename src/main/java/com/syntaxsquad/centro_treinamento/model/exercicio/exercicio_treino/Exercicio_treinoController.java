package com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino;

import com.syntaxsquad.centro_treinamento.model.exercicio.Exercicio;

import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercicio-treino")
public class Exercicio_treinoController {

    @Autowired
    private Exercicio_treinoService exercicioTreinoService;

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio_treinoResponse> getExercicio_treinoById(@PathVariable UUID id) {
        Exercicio_treino exercicioTreino = exercicioTreinoService.findById(id);
        if (exercicioTreino == null) {
            return ResponseEntity.notFound().build();
        }
        Exercicio_treinoResponse response = new Exercicio_treinoResponse(
                exercicioTreino.getId(),
                exercicioTreino.getExercicio().getId(),
                exercicioTreino.getTreino().getId(),
                exercicioTreino.getRepeticao(),
                exercicioTreino.getSerie(),
                exercicioTreino.getCarga(),
                exercicioTreino.getDescanso()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Exercicio_treinoResponse>> getAllExercicio_treino() {
        List<Exercicio_treino> exercicioTreinoList = exercicioTreinoService.findAll();
        List<Exercicio_treinoResponse> responses = exercicioTreinoList.stream()
                .map(exercicioTreino -> new Exercicio_treinoResponse(
                        exercicioTreino.getId(),
                        exercicioTreino.getExercicio().getId(),
                        exercicioTreino.getTreino().getId(),
                        exercicioTreino.getRepeticao(),
                        exercicioTreino.getSerie(),
                        exercicioTreino.getCarga(),
                        exercicioTreino.getDescanso()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Exercicio_treinoResponse> createExercicio_treino(@RequestBody Exercicio_treinoRequest exercicioTreinoRequest) {
        Exercicio_treino savedExercicioTreino = exercicioTreinoService.save(exercicioTreinoRequest);
        Exercicio_treinoResponse response = new Exercicio_treinoResponse(
                savedExercicioTreino.getId(),
                savedExercicioTreino.getExercicio().getId(),
                savedExercicioTreino.getTreino().getId(),
                savedExercicioTreino.getRepeticao(),
                savedExercicioTreino.getSerie(),
                savedExercicioTreino.getCarga(),
                savedExercicioTreino.getDescanso()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio_treinoResponse> updateExercicio_treino(@PathVariable UUID id, @RequestBody Exercicio_treinoRequest exercicioTreinoRequest) {
        Exercicio_treino updatedExercicioTreino = exercicioTreinoService.update(id, exercicioTreinoRequest);
        if (updatedExercicioTreino == null) {
            return ResponseEntity.notFound().build();
        }
        Exercicio_treinoResponse response = new Exercicio_treinoResponse(
                updatedExercicioTreino.getId(),
                updatedExercicioTreino.getExercicio().getId(),
                updatedExercicioTreino.getTreino().getId(),
                updatedExercicioTreino.getRepeticao(),
                updatedExercicioTreino.getSerie(),
                updatedExercicioTreino.getCarga(),
                updatedExercicioTreino.getDescanso()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercicio_treino(@PathVariable UUID id) {
        boolean deleted = exercicioTreinoService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
