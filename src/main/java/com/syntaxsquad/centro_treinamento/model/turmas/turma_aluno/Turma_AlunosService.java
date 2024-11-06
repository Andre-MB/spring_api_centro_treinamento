package com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno;

import com.syntaxsquad.centro_treinamento.model.turmas.turma_aluno.Turma_Alunos;
import com.syntaxsquad.centro_treinamento.model.turmas.Turma;
import com.syntaxsquad.centro_treinamento.model.turmas.TurmaRepository;
import com.syntaxsquad.centro_treinamento.model.alunos.AlunoRepository;
import com.syntaxsquad.centro_treinamento.model.alunos.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;
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
    private AlunoRepository alunosRepository;

    public Turma_Alunos save(Turma_AlunosRequest turmaAlunosRequest) {
        Turma turma = turmaRepository.findById(turmaAlunosRequest.getTurmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma not found"));

        Alunos aluno = alunosRepository.findByCpf(turmaAlunosRequest.getAlunoCpf())
                .orElseThrow(() -> new IllegalArgumentException("Aluno not found"));

        Turma_Alunos turmaAlunos = new Turma_Alunos();
        turmaAlunos.setTurma(turma);
        turmaAlunos.setAluno(aluno);

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

        Alunos aluno = alunosRepository.findByCpf(turmaAlunosRequest.getAlunoCpf())
                .orElseThrow(() -> new IllegalArgumentException("Aluno not found"));

        turmaAlunos.setTurma(turma);
        turmaAlunos.setAluno(aluno);

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
