package com.syntaxsquad.centro_treinamento.model.notificacoes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/notificacoes")
public class NotificacoesController {

    @Autowired
    private NotificacoesService notificacoesService;

    // Criar Notificação
    @PostMapping
    public ResponseEntity<NotificacoesResponse> criarNotificacao(@RequestBody NotificacoesRequest request) {
        NotificacoesResponse response = notificacoesService.criarNotificacao(request);
        return ResponseEntity.ok(response);
    }

    // Buscar Notificação por ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificacoesResponse> buscarNotificacao(@PathVariable UUID id) {
        Optional<NotificacoesResponse> notificacao = notificacoesService.buscarNotificacao(id);
        return notificacao.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar Notificação por ID
    @PutMapping("/{id}")
    public ResponseEntity<NotificacoesResponse> atualizarNotificacao(@PathVariable UUID id,
                                                                     @RequestBody NotificacoesRequest request) {
        Optional<NotificacoesResponse> updatedNotificacao = notificacoesService.atualizarNotificacao(id, request);
        return updatedNotificacao.map(ResponseEntity::ok)
                                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar Notificação por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNotificacao(@PathVariable UUID id) {
        boolean deleted = notificacoesService.deletarNotificacao(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Listar todas as notificações
    @GetMapping
    public ResponseEntity<List<NotificacoesResponse>> listarNotificacoes() {
        List<NotificacoesResponse> notificacoes = notificacoesService.listarNotificacoes();
        return ResponseEntity.ok(notificacoes);
    }
}
