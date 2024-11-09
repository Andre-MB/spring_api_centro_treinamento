package com.syntaxsquad.centro_treinamento.model.turmas;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository; // Repositório de User
import com.syntaxsquad.centro_treinamento.model.treino.TreinoRepository;
import com.syntaxsquad.centro_treinamento.model.turmas.Turma;
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
    private UserRepository userRepository; // Repositório de User

    @Autowired
    private TreinoRepository treinoRepository;

    public Turma save(TurmaRequest turmaRequest) {
        // Aqui buscamos um User e verificamos se ele tem o papel de "trainer"
        User user = userRepository.findActiveUserByCpf(turmaRequest.getTrainer_Cpf())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Verifica se o usuário possui o papel de "trainer" na lista de authorities
        if (!user.getRole().toString().equals("TRAINER")) {
            throw new IllegalArgumentException("User is not a trainer");
        }

        Treino treino = treinoRepository.findById(turmaRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        Turma turma = new Turma();
        turma.setUser(user); // Associamos o User à turma
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

        // Aqui verificamos se o User tem o papel de "trainer"
        User user = userRepository.findActiveUserByCpf(turmaRequest.getTrainer_Cpf())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Verifica se o usuário tem o papel de "trainer"
        if (!user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_TRAINER"))) {
            throw new IllegalArgumentException("User is not a trainer");
        }

        Treino treino = treinoRepository.findById(turmaRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        turma.setUser(user); // Associamos o User à turma
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
