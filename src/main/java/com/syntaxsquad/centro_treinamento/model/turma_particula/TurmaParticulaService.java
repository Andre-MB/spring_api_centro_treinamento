package com.syntaxsquad.centro_treinamento.model.turma_particula;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurmaParticulaService {

    @Autowired
    private TurmaParticulaRepository turmaParticulaRepository;

    @Autowired
    private UserRepository userRepository;

    // Criar a relação entre treinador e aluno
    public TurmaParticula createTurmaParticula(TurmaParticulaRequest request) {
        // Buscar o treinador
        User trainer = userRepository.findActiveUserByCpf(request.getTrainer_cpf())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        if (!trainer.getRole().toString().equals("TRAINER")) {
            throw new IllegalArgumentException("User is not a trainer");
        }
        
        // Buscar o aluno (corrigido para usar aluno_cpf)
        User aluno = userRepository.findActiveUserByCpf(request.getAluno_cpf())
                .orElseThrow(() -> new RuntimeException("Aluno not found"));
        if (!aluno.getRole().toString().equals("STUDENT")) {
            throw new IllegalArgumentException("User is not a student");
        }

        // Verificar se a relação entre treinador e aluno já existe
        if (turmaParticulaRepository.existsByTrainer_CpfAndAluno_Cpf(trainer.getCpf(), aluno.getCpf())) {
            throw new RuntimeException("Relacionamento já existe.");
        }

        // Criar e salvar a TurmaParticula
        TurmaParticula turmaParticula = new TurmaParticula();
        turmaParticula.setTrainer(trainer);
        turmaParticula.setAluno(aluno);

        return turmaParticulaRepository.save(turmaParticula);
    }

    // Buscar TurmaParticula por ID
    public TurmaParticula findById(UUID id) {
        return turmaParticulaRepository.findById(id).orElse(null);
    }

    // Buscar todas as TurmaParticulas
    public List<TurmaParticula> findAll() {
        return turmaParticulaRepository.findAll();
    }

    // Atualizar a relação entre treinador e aluno
    public TurmaParticula updateTurmaParticula(UUID id, TurmaParticulaRequest request) {
        Optional<TurmaParticula> optionalTurmaParticula = turmaParticulaRepository.findById(id);
        if (optionalTurmaParticula.isEmpty()) {
            return null;
        }

        TurmaParticula turmaParticula = optionalTurmaParticula.get();
        // Buscar o treinador
        User trainer = userRepository.findActiveUserByCpf(request.getTrainer_cpf())
                .orElseThrow(() -> new RuntimeException("Trainer not found"));
        if (!trainer.getRole().toString().equals("TRAINER")) {
            throw new IllegalArgumentException("User is not a trainer");
        }
        
        // Buscar o aluno
        User aluno = userRepository.findActiveUserByCpf(request.getAluno_cpf())
                .orElseThrow(() -> new RuntimeException("Aluno not found"));
        if (!aluno.getRole().toString().equals("STUDENT")) {
            throw new IllegalArgumentException("User is not a student");
        }

        // Atualizar a relação
        turmaParticula.setTrainer(trainer);
        turmaParticula.setAluno(aluno);

        return turmaParticulaRepository.save(turmaParticula);
    }

    // Deletar TurmaParticula por ID
    public boolean deleteById(UUID id) {
        if (turmaParticulaRepository.existsById(id)) {
            turmaParticulaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
