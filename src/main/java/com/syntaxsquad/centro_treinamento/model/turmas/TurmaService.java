package com.syntaxsquad.centro_treinamento.model.turmas;

import com.syntaxsquad.centro_treinamento.model.turmas.TurmaRequest;
import com.syntaxsquad.centro_treinamento.model.turmas.TurmaResponse;
import com.syntaxsquad.centro_treinamento.model.turmas.TurmaService;
import com.syntaxsquad.centro_treinamento.model.trainer.TrainerRepository;
import com.syntaxsquad.centro_treinamento.model.treino.TreinoRepository;
import com.syntaxsquad.centro_treinamento.model.turmas.Turma;
import com.syntaxsquad.centro_treinamento.model.trainer.Trainer;
import com.syntaxsquad.centro_treinamento.model.treino.Treino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    public Turma save(TurmaRequest turmaRequest) {
        Trainer trainer = trainerRepository.findByCpf(turmaRequest.getTrainerCpf())
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        Treino treino = treinoRepository.findById(turmaRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        Turma turma = new Turma();
        turma.setTrainer(trainer);
        turma.setTreino(treino);
        turma.setHorario(turmaRequest.getHorario());

        return turmaRepository.save(turma);
    }

    public Turma findById(UUID id) {
        return turmaRepository.findById(id).orElse(null);
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma update(UUID id, TurmaRequest turmaRequest) {
        Turma turma = turmaRepository.findById(id).orElse(null);
        if (turma == null) {
            return null;
        }

        Trainer trainer = trainerRepository.findByCpf(turmaRequest.getTrainerCpf())
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        Treino treino = treinoRepository.findById(turmaRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        turma.setTrainer(trainer);
        turma.setTreino(treino);
        turma.setHorario(turmaRequest.getHorario());

        return turmaRepository.save(turma);
    }

    public boolean deleteById(UUID id) {
        Turma turma = turmaRepository.findById(id).orElse(null);
        if (turma == null) {
            return false;
        }
        turmaRepository.delete(turma);
        return true;
    }
}
