package com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno;

import com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno.Turma_Alunos;
import com.syntaxsquad.centro_treinamento.model.turmas.Turma;
import com.syntaxsquad.centro_treinamento.model.turmas.TurmaRepository;
import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository; // Repositório de User
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Turma_AlunosService {

    @Autowired
    private Turma_AlunosRepository turmaAlunosRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private UserRepository userRepository; // Repositório de User

    public Turma_Alunos save(Turma_AlunosRequest turmaAlunosRequest) {
        Turma turma = turmaRepository.findById(turmaAlunosRequest.getTurmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma not found"));

        // Verifica se o usuário é um aluno
        User user = userRepository.findActiveUserByCpf(turmaAlunosRequest.getAlunoCpf())
                .orElseThrow(() -> new IllegalArgumentException("Aluno (User) not found"));

        // Verifica se o papel do usuário é "STUDENT"
        if (!user.getRole().toString().equals("STUDENT")) {
            throw new IllegalArgumentException("User is not a student");
        }
        Turma_Alunos turmaAlunos = new Turma_Alunos();
        turmaAlunos.setTurma(turma);
        turmaAlunos.setUser(user);  // Associando o usuário à turma

        return turmaAlunosRepository.save(turmaAlunos);
    }

    public Turma_Alunos findById(UUID id) {
        return turmaAlunosRepository.findById(id).orElse(null);
    }

    public List<Turma_Alunos> findAll() {
        return turmaAlunosRepository.findAll();
    }

    public Turma_Alunos update(UUID id, Turma_AlunosRequest turmaAlunosRequest) {
        Turma_Alunos turmaAlunos = turmaAlunosRepository.findById(id).orElse(null);
        if (turmaAlunos == null) {
            return null;
        }

        Turma turma = turmaRepository.findById(turmaAlunosRequest.getTurmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma not found"));

        // Verifica se o usuário é um aluno
        User user = userRepository.findActiveUserByCpf(turmaAlunosRequest.getAlunoCpf())
                .orElseThrow(() -> new IllegalArgumentException("Aluno (User) not found"));

        // Verifica se o papel do usuário é "ROLE_STUDENT"
        if (!user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT"))) {
            throw new IllegalArgumentException("User is not a student");
        }

        turmaAlunos.setTurma(turma);
        turmaAlunos.setUser(user);  // Associando o usuário à turma

        return turmaAlunosRepository.save(turmaAlunos);
    }

    public boolean deleteById(UUID id) {
        Turma_Alunos turmaAlunos = turmaAlunosRepository.findById(id).orElse(null);
        if (turmaAlunos == null) {
            return false;
        }
        turmaAlunosRepository.delete(turmaAlunos);
        return true;
    }
}
