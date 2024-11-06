package com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino;

import com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino.Exercicio_treino;
import com.syntaxsquad.centro_treinamento.model.exercicio.Exercicio;
import com.syntaxsquad.centro_treinamento.model.treino.Treino;
import com.syntaxsquad.centro_treinamento.model.exercicio.ExercicioRequest;
import com.syntaxsquad.centro_treinamento.model.exercicio.ExercicioRepository;
import com.syntaxsquad.centro_treinamento.model.treino.TreinoRepository;
import com.syntaxsquad.centro_treinamento.model.exercicio.exercicio_treino.Exercicio_treinoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Exercicio_treinoService {

    @Autowired
    private Exercicio_treinoRepository exercicioTreinoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    public Exercicio_treino save(Exercicio_treinoRequest exercicioTreinoRequest) {
        Exercicio exercicio = exercicioRepository.findById(exercicioTreinoRequest.getExercicioId())
                .orElseThrow(() -> new IllegalArgumentException("Exercicio not found"));

        Treino treino = treinoRepository.findById(exercicioTreinoRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        Exercicio_treino exercicioTreino = new Exercicio_treino();
        exercicioTreino.setExercicio(exercicio);
        exercicioTreino.setTreino(treino);
        exercicioTreino.setRepeticao(exercicioTreinoRequest.getRepeticao());
        exercicioTreino.setSerie(exercicioTreinoRequest.getSerie());
        exercicioTreino.setCarga(exercicioTreinoRequest.getCarga());
        exercicioTreino.setDescanso(exercicioTreinoRequest.getDescanso());

        return exercicioTreinoRepository.save(exercicioTreino);
    }

    public Exercicio_treino findById(UUID id) {
        return exercicioTreinoRepository.findById(id).orElse(null);
    }

    public List<Exercicio_treino> findAll() {
        return exercicioTreinoRepository.findAll();
    }

    public Exercicio_treino update(UUID id, Exercicio_treinoRequest exercicioTreinoRequest) {
        Exercicio_treino exercicioTreino = exercicioTreinoRepository.findById(id).orElse(null);
        if (exercicioTreino == null) {
            return null;
        }

        Exercicio exercicio = exercicioRepository.findById(exercicioTreinoRequest.getExercicioId())
                .orElseThrow(() -> new IllegalArgumentException("Exercicio not found"));

        Treino treino = treinoRepository.findById(exercicioTreinoRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        exercicioTreino.setExercicio(exercicio);
        exercicioTreino.setTreino(treino);
        exercicioTreino.setRepeticao(exercicioTreinoRequest.getRepeticao());
        exercicioTreino.setSerie(exercicioTreinoRequest.getSerie());
        exercicioTreino.setCarga(exercicioTreinoRequest.getCarga());
        exercicioTreino.setDescanso(exercicioTreinoRequest.getDescanso());

        return exercicioTreinoRepository.save(exercicioTreino);
    }

    public boolean deleteById(UUID id) {
        Exercicio_treino exercicioTreino = exercicioTreinoRepository.findById(id).orElse(null);
        if (exercicioTreino == null) {
            return false;
        }
        exercicioTreinoRepository.delete(exercicioTreino);
        return true;
    }
}
