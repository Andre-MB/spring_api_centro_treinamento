package com.syntaxsquad.centro_treinamento.model.mensalidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.syntaxsquad.centro_treinamento.model.alunos.Alunos;

import java.util.List;

@RestController
@RequestMapping("/mensalidades")
public class MensalidadeController {

    @Autowired
    private MensalidadeService mensalidadeService;

    // Criar uma nova mensalidade
    @PostMapping
    public ResponseEntity<MensalidadeResponse> createMensalidade(@RequestBody MensalidadeRequest request) {
        MensalidadeResponse response = mensalidadeService.createMensalidade(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
    }

    // Buscar mensalidades por CPF (retorna uma lista)
    @GetMapping("/{cpf}")
    public ResponseEntity<List<MensalidadeResponse>> getMensalidadeByCpf(@PathVariable String cpf) {
        List<MensalidadeResponse> response = mensalidadeService.getMensalidadeByCpf(cpf); // Retorna lista
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Caso não encontre, 404 Not Found
                                 .body(response);
        }
        return ResponseEntity.ok(response); // Retorna 200 OK com a lista de mensalidades
    }

    // Buscar todas as mensalidades
    @GetMapping
    public ResponseEntity<List<MensalidadeResponse>> getAllMensalidades() {
        List<MensalidadeResponse> response = mensalidadeService.getAllMensalidades();
        return ResponseEntity.ok(response); // Retorna 200 OK com todas as mensalidades
    }

    // Atualizar uma mensalidade
    @PutMapping("/{cpf}")
    public ResponseEntity<MensalidadeResponse> updateMensalidade(
            @PathVariable String cpf, @RequestBody MensalidadeRequest request) {
        MensalidadeResponse response = mensalidadeService.updateMensalidade(cpf, request);
        return ResponseEntity.ok(response); // Retorna 200 OK com a mensalidade atualizada
    }

    // Deletar uma mensalidade
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteMensalidade(@PathVariable String cpf) {
        try {
            mensalidadeService.deleteMensalidade(cpf);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content após deletar
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Retorna 404 caso não encontre a mensalidade
                                 .build();
        }
    }
}
