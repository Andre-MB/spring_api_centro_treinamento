package com.syntaxsquad.centro_treinamento.model.trainer;

import com.syntaxsquad.centro_treinamento.model.trainer.Trainer;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/{cpf}")
    public ResponseEntity<TrainerResponse> getTrainerByCpf(@PathVariable String cpf) {
        Trainer trainer = trainerService.findByCpf(cpf);
        if (trainer == null) {
            return ResponseEntity.notFound().build();
        }
        TrainerResponse response = new TrainerResponse(
                trainer.getCpf(),
                trainer.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> getAllTrainers() {
        List<Trainer> trainers = trainerService.findAll();
        List<TrainerResponse> responses = trainers.stream()
                .map(trainer -> new TrainerResponse(
                        trainer.getCpf(),
                        trainer.getUser().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<TrainerResponse> createTrainer(@RequestBody TrainerRequest trainerRequest) {
        Trainer savedTrainer = trainerService.save(trainerRequest);
        TrainerResponse response = new TrainerResponse(
                savedTrainer.getCpf(),
                savedTrainer.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<TrainerResponse> updateTrainer(@PathVariable String cpf, @RequestBody TrainerRequest trainerRequest) {
        Trainer updatedTrainer = trainerService.update(cpf, trainerRequest);
        if (updatedTrainer == null) {
            return ResponseEntity.notFound().build();
        }
        TrainerResponse response = new TrainerResponse(
                updatedTrainer.getCpf(),
                updatedTrainer.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable String cpf) {
        boolean deleted = trainerService.deleteByCpf(cpf);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
