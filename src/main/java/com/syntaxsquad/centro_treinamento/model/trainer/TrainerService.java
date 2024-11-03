package com.syntaxsquad.centro_treinamento.model.trainer;

import jakarta.persistence.Entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer createTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer getTrainerByCpf(String cpf) {
        Optional<Trainer> trainer = trainerRepository.findById(cpf);
        return trainer.orElse(null);
    }

    public Trainer updateTrainer(String cpf, Trainer trainer) {
        if (trainerRepository.existsById(cpf)) {
            trainer.setCpf(cpf); // Atualiza o CPF no objeto Trainer
            return trainerRepository.save(trainer);
        }
        return null;
    }

    public boolean deleteTrainer(String cpf) {
        if (trainerRepository.existsById(cpf)) {
            trainerRepository.deleteById(cpf);
            return true;
        }
        return false;
    }
}
