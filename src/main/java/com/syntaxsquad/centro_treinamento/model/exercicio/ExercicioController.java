package com.syntaxsquad.centro_treinamento.model.exercicio;

import com.syntaxsquad.centro_treinamento.model.exercicio.Exercicio;
import com.syntaxsquad.centro_treinamento.model.exercicio.ExercicioService;
import com.syntaxsquad.centro_treinamento.model.exercicio.ExercicioResponse;
import com.syntaxsquad.centro_treinamento.model.exercicio.ExercicioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exercicios")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping("/{id}")
    public ResponseEntity<ExercicioResponse> getExercicioById(@PathVariable UUID id) {
        Exercicio exercicio = exercicioService.findById(id);
        if (exercicio == null) {
            return ResponseEntity.notFound().build();
        }
        ExercicioResponse response = new ExercicioResponse(
                exercicio.getId(),
                exercicio.getNomeExercicio(),
                exercicio.getCategoria(),
                exercicio.getGrupoMuscular()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ExercicioResponse>> getAllExercicios() {
        List<Exercicio> exercicios = exercicioService.findAll();
        List<ExercicioResponse> responses = exercicios.stream()
                .map(exercicio -> new ExercicioResponse(
                        exercicio.getId(),
                        exercicio.getNomeExercicio(),
                        exercicio.getCategoria(),
                        exercicio.getGrupoMuscular()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<ExercicioResponse> createExercicio(@RequestBody ExercicioRequest exercicioRequest) {
        Exercicio savedExercicio = exercicioService.save(exercicioRequest);
        ExercicioResponse response = new ExercicioResponse(
                savedExercicio.getId(),
                savedExercicio.getNomeExercicio(),
                savedExercicio.getCategoria(),
                savedExercicio.getGrupoMuscular()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExercicioResponse> updateExercicio(@PathVariable UUID id, @RequestBody ExercicioRequest exercicioRequest) {
        Exercicio updatedExercicio = exercicioService.update(id, exercicioRequest);
        if (updatedExercicio == null) {
            return ResponseEntity.notFound().build();
        }
        ExercicioResponse response = new ExercicioResponse(
                updatedExercicio.getId(),
                updatedExercicio.getNomeExercicio(),
                updatedExercicio.getCategoria(),
                updatedExercicio.getGrupoMuscular()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercicio(@PathVariable UUID id) {
        boolean deleted = exercicioService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
