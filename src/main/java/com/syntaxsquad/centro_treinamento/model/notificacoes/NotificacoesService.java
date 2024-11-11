package com.syntaxsquad.centro_treinamento.model.notificacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificacoesService {

    @Autowired
    private NotificacoesRepository notificacoesRepository;

    // Criar nova notificação
    public NotificacoesResponse criarNotificacao(NotificacoesRequest request) {
        Notificacoes notificacao = new Notificacoes();
        notificacao.setTitule(request.getTitule());
        notificacao.setMensagem(request.getMensagem());

        Notificacoes savedNotificacao = notificacoesRepository.save(notificacao);
        return new NotificacoesResponse(savedNotificacao.getId(),
                                        savedNotificacao.getTitule(),
                                        savedNotificacao.getMensagem(),
                                        savedNotificacao.getCreatedAt());
    }

    // Buscar notificação por ID
    public Optional<NotificacoesResponse> buscarNotificacao(UUID id) {
        Optional<Notificacoes> notificacao = notificacoesRepository.findById(id);
        return notificacao.map(n -> new NotificacoesResponse(n.getId(), n.getTitule(), n.getMensagem(), n.getCreatedAt()));
    }

    // Atualizar notificação por ID
    public Optional<NotificacoesResponse> atualizarNotificacao(UUID id, NotificacoesRequest request) {
        Optional<Notificacoes> notificacaoOptional = notificacoesRepository.findById(id);
        if (notificacaoOptional.isPresent()) {
            Notificacoes notificacao = notificacaoOptional.get();
            notificacao.setTitule(request.getTitule());
            notificacao.setMensagem(request.getMensagem());

            Notificacoes updatedNotificacao = notificacoesRepository.save(notificacao);
            return Optional.of(new NotificacoesResponse(updatedNotificacao.getId(),
                                                       updatedNotificacao.getTitule(),
                                                       updatedNotificacao.getMensagem(),
                                                       updatedNotificacao.getCreatedAt()));
        }
        return Optional.empty();
    }

    // Deletar notificação por ID
    public boolean deletarNotificacao(UUID id) {
        if (notificacoesRepository.existsById(id)) {
            notificacoesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Listar todas as notificações
    public List<NotificacoesResponse> listarNotificacoes() {
        List<Notificacoes> notificacoes = notificacoesRepository.findAll();
        return notificacoes.stream()
                           .map(n -> new NotificacoesResponse(n.getId(), n.getTitule(), n.getMensagem(), n.getCreatedAt()))
                           .collect(Collectors.toList());
    }
}
