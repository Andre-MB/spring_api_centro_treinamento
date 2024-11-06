package com.syntaxsquad.centro_treinamento.model.exercicio;

import com.syntaxsquad.centro_treinamento.model.exercicio.Exercicio;
import com.syntaxsquad.centro_treinamento.model.exercicio.ExercicioRepository;
import com.syntaxsquad.centro_treinamento.model.exercicio.ExercicioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public Exercicio save(ExercicioRequest exercicioRequest) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNomeExercicio(exercicioRequest.getNomeExercicio());
        exercicio.setCategoria(exercicioRequest.getCategoria());
        exercicio.setGrupoMuscular(exercicioRequest.getGrupoMuscular());

        return exercicioRepository.save(exercicio);
    }

    public Exercicio findById(UUID id) {
        return exercicioRepository.findById(id).orElse(null);
    }

    public List<Exercicio> findAll() {
        return exercicioRepository.findAll();
    }

    public Exercicio update(UUID id, ExercicioRequest exercicioRequest) {
        Exercicio exercicio = exercicioRepository.findById(id).orElse(null);
        if (exercicio == null) {
            return null;
        }

        exercicio.setNomeExercicio(exercicioRequest.getNomeExercicio());
        exercicio.setCategoria(exercicioRequest.getCategoria());
        exercicio.setGrupoMuscular(exercicioRequest.getGrupoMuscular());

        return exercicioRepository.save(exercicio);
    }

    public boolean deleteById(UUID id) {
        Exercicio exercicio = exercicioRepository.findById(id).orElse(null);
        if (exercicio == null) {
            return false;
        }
        exercicioRepository.delete(exercicio);
        return true;
    }
}
