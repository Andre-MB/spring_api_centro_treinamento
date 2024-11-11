package com.syntaxsquad.centro_treinamento.model.ficha_de_avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/FichaAvaliacao")
public class FichaAvaliacaoController {

    @Autowired
    private FichaAvaliacaoService fichaAvaliacaoService;

    @Autowired
    private FichaAvaliacaoRepository fichaAvaliacaoRepository;

    // Endpoint para criar uma nova ficha de avaliação
    @PostMapping
    public ResponseEntity<FichaAvaliacaoResponse> createFicha(@RequestBody FichaAvaliacaoRequest request) {
        FichaAvaliacaoResponse response = fichaAvaliacaoService.createFicha(request);
        return ResponseEntity.ok(response);
    }

    // Endpoint para atualizar uma ficha de avaliação existente
    @PutMapping("/{id}")
    public ResponseEntity<FichaAvaliacaoResponse> updateFicha(@PathVariable Long id, @RequestBody FichaAvaliacaoRequest request) {
        FichaAvaliacaoResponse response = fichaAvaliacaoService.updateFicha(id, request);
        return ResponseEntity.ok(response);
    }

    // Novo endpoint para buscar uma ficha de avaliação pelo CPF do usuário
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<FichaAvaliacaoResponse> getFichaByCpf(@PathVariable String cpf) {
        FichaAvaliacaoResponse response = fichaAvaliacaoService.getFichaByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicha(@PathVariable Long id) {
        fichaAvaliacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
