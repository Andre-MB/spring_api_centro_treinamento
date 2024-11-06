package com.syntaxsquad.centro_treinamento.model.treino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    // Criar um novo treino
    @PostMapping
    public ResponseEntity<TreinoResponse> createTreino(@RequestBody TreinoRequest request) {
        TreinoResponse response = treinoService.createTreino(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
    }

    // Obter todos os treinos
    @GetMapping
    public ResponseEntity<List<TreinoResponse>> getAllTreinos() {
        List<TreinoResponse> response = treinoService.getAllTreinos();
        return ResponseEntity.ok(response); // 200 OK
    }

    // Obter treino por ID
    @GetMapping("/{id}")
    public ResponseEntity<TreinoResponse> getTreinoById(@PathVariable UUID id) {
        TreinoResponse response = treinoService.getTreinoById(id);
        return ResponseEntity.ok(response); // 200 OK
    }

    // Atualizar treino
    @PutMapping("/{id}")
    public ResponseEntity<TreinoResponse> updateTreino(@PathVariable UUID id, @RequestBody TreinoRequest request) {
        TreinoResponse response = treinoService.updateTreino(id, request);
        return ResponseEntity.ok(response); // 200 OK
    }

    // Deletar treino
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreino(@PathVariable UUID id) {
        treinoService.deleteTreino(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
