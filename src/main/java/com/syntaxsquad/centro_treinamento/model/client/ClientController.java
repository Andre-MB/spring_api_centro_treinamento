package com.syntaxsquad.centro_treinamento.model.client;

import com.syntaxsquad.centro_treinamento.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientResponse> getClientByCpf(@PathVariable String cpf) {
        Client client = clientService.findByCpf(cpf);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        ClientResponse response = new ClientResponse(
                client.getCpf(),
                client.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<Client> clients = clientService.findAll();
        List<ClientResponse> responses = clients.stream()
                .map(client -> new ClientResponse(
                        client.getCpf(),
                        client.getUser().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest) {
        Client savedClient = clientService.save(clientRequest);
        ClientResponse response = new ClientResponse(
                savedClient.getCpf(),
                savedClient.getUser().getId()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable String cpf, @RequestBody ClientRequest clientRequest) {
        Client updatedClient = clientService.update(cpf, clientRequest);
        if (updatedClient == null) {
            return ResponseEntity.notFound().build();
        }
        ClientResponse response = new ClientResponse(
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
