package com.syntaxsquad.centro_treinamento.model.user.endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> getEnderecoById(@PathVariable UUID id) {
        Endereco endereco = enderecoService.findById(id);
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        EnderecoResponse response = new EnderecoResponse(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getPais(),
                endereco.getComplemento(),
                endereco.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> getAllEnderecos() {
        List<Endereco> enderecos = enderecoService.findAll();
        List<EnderecoResponse> responses = enderecos.stream()
                .map(endereco -> new EnderecoResponse(
                        endereco.getId(),
                        endereco.getCep(),
                        endereco.getLogradouro(),
                        endereco.getBairro(),
                        endereco.getCidade(),
                        endereco.getEstado(),
                        endereco.getPais(),
                        endereco.getComplemento(),
                        endereco.getUser().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<EnderecoResponse> createEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        Endereco savedEndereco = enderecoService.save(enderecoRequest);
        EnderecoResponse response = new EnderecoResponse(
                savedEndereco.getId(),
                savedEndereco.getCep(),
                savedEndereco.getLogradouro(),
                savedEndereco.getBairro(),
                savedEndereco.getCidade(),
                savedEndereco.getEstado(),
                savedEndereco.getPais(),
                savedEndereco.getComplemento(),
                savedEndereco.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponse> updateEndereco(@PathVariable UUID id, @RequestBody EnderecoRequest enderecoRequest) {
        Endereco updatedEndereco = enderecoService.update(id, enderecoRequest);
        if (updatedEndereco == null) {
            return ResponseEntity.notFound().build();
        }
        EnderecoResponse response = new EnderecoResponse(
                updatedEndereco.getId(),
                updatedEndereco.getCep(),
                updatedEndereco.getLogradouro(),
                updatedEndereco.getBairro(),
                updatedEndereco.getCidade(),
                updatedEndereco.getEstado(),
                updatedEndereco.getPais(),
                updatedEndereco.getComplemento(),
                updatedEndereco.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable UUID id) {
        boolean deleted = enderecoService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
