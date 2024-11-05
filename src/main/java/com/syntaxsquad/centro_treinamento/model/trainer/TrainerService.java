package com.syntaxsquad.centro_treinamento.model.trainer;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private UserRepository userRepository;

    public Trainer save(TrainerRequest trainerRequest) {
        User user = userRepository.findById(trainerRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trainer trainer = new Trainer();
        trainer.setCpf(trainerRequest.getCpf());
        trainer.setUser(user);
        
        return trainerRepository.save(trainer);
    }

    public Trainer findByCpf(String cpf) {
        return trainerRepository.findById(cpf).orElse(null);
    }

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer update(String cpf, TrainerRequest trainerRequest) {
        Trainer trainer = trainerRepository.findById(cpf).orElse(null);
        if (trainer == null) {
            return null;
        }

        User user = userRepository.findById(trainerRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        trainer.setUser(user);
        trainer.setCpf(trainerRequest.getCpf());

        return trainerRepository.save(trainer);
    }

    public boolean deleteByCpf(String cpf) {
        Trainer trainer = trainerRepository.findById(cpf).orElse(null);
        if (trainer == null) {
            return false;
        }
        trainerRepository.delete(trainer);
        return true;
    }
}
