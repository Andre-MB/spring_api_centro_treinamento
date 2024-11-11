package com.syntaxsquad.centro_treinamento.model.turmas;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import com.syntaxsquad.centro_treinamento.model.treino.TreinoRepository;
import com.syntaxsquad.centro_treinamento.model.treino.Treino;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TurmaService {

    private static final Logger logger = LoggerFactory.getLogger(TurmaService.class);

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private UserRepository userRepository; // Repositório de User

    @Autowired
    private TreinoRepository treinoRepository;

    // Método para validar o papel de 'trainer' do usuário
    private void validateTrainerRole(User user) {
        if (!user.getRole().toString().equals("TRAINER")) {
            throw new IllegalArgumentException("User is not a trainer");
        }
    }

    public Turma save(TurmaRequest turmaRequest) {
        User user = userRepository.findActiveUserByCpf(turmaRequest.getTrainer_Cpf())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
                if (!user.getRole().toString().equals("TRAINER")) {
                    throw new IllegalArgumentException("User is not a trainer");
                }

        validateTrainerRole(user);

        Treino treino = treinoRepository.findById(turmaRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        Turma turma = new Turma();
        turma.setUser(user);
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

        User user = userRepository.findActiveUserByCpf(turmaRequest.getTrainer_Cpf())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
                if (!user.getRole().toString().equals("TRAINER")) {
                    throw new IllegalArgumentException("User is not a trainer");
                }

        validateTrainerRole(user);

        Treino treino = treinoRepository.findById(turmaRequest.getTreinoId())
                .orElseThrow(() -> new IllegalArgumentException("Treino not found"));

        turma.setUser(user);
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

    // Método para excluir turmas vencidas
    public void deleteExpiredTurmas() {
        // Obtém a data e hora atuais
        LocalDateTime currentDateTime = LocalDateTime.now();
        logger.info("Data e hora atual: {}", currentDateTime);

        // Obtém as turmas cujos horários já passaram
        List<Turma> expiredTurmas = turmaRepository.findByHorarioBefore(currentDateTime);
        logger.info("Total de turmas vencidas encontradas: {}", expiredTurmas.size());

        // Adiciona um log detalhado para cada turma vencida encontrada
        expiredTurmas.forEach(turma -> 
            logger.info("Turma vencida: ID = {}, Horário = {}", turma.getId(), turma.getHorario())
        );

        // Exclui as turmas vencidas
        if (!expiredTurmas.isEmpty()) {
            turmaRepository.deleteAll(expiredTurmas);
            logger.info("Turmas vencidas apagadas.");
        } else {
            logger.info("Nenhuma turma vencida encontrada para exclusão.");
        }
    }

    @PostConstruct
    public void init() {
        // Apaga as turmas vencidas logo após a inicialização, sem esperar
        deleteExpiredTurmas();
        logger.info("Primeira verificação e exclusão das turmas vencidas realizada imediatamente");
    }

    // Agendando a execução para rodar a cada 10 minutos (600000ms)
    @Scheduled(fixedRate = 600000, initialDelay = 60000)  // Executa a cada 10 minutos, mas começa após 1 minuto
    public void scheduleTask() {
        // Antes de excluir, verifica e imprime o log para ajudar no rastreamento
        logger.info("Iniciando verificação e exclusão de turmas vencidas.");
        deleteExpiredTurmas();  // Executa a verificação e exclusão das turmas vencidas
        logger.info("Verificação e exclusão das turmas vencidas concluída.");
    }
}
