package com.syntaxsquad.centro_treinamento.model.alunos;

import com.syntaxsquad.centro_treinamento.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService clientService;

    @GetMapping("/{cpf}")
    public ResponseEntity<AlunoResponse> getClientByCpf(@PathVariable String cpf) {
        Alunos client = clientService.findByCpf(cpf);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        AlunoResponse response = new AlunoResponse(
                client.getCpf(),
                client.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> getAllClients() {
        List<Alunos> clients = clientService.findAll();
        List<AlunoResponse> responses = clients.stream()
                .map(client -> new AlunoResponse(
                        client.getCpf(),
                        client.getUser().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> createClient(@RequestBody AlunoRequest clientRequest) {
        Alunos savedClient = clientService.save(clientRequest);
        AlunoResponse response = new AlunoResponse(
                savedClient.getCpf(),
                savedClient.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<AlunoResponse> updateClient(@PathVariable String cpf, @RequestBody AlunoRequest clientRequest) {
        Alunos updatedClient = clientService.update(cpf, clientRequest);
        if (updatedClient == null) {
            return ResponseEntity.notFound().build();
        }
        AlunoResponse response = new AlunoResponse(
                updatedClient.getCpf(),
                updatedClient.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteClient(@PathVariable String cpf) {
        boolean deleted = clientService.deleteByCpf(cpf);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
