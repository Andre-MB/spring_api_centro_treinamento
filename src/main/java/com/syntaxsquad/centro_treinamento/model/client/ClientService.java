package com.syntaxsquad.centro_treinamento.model.client;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;

import jakarta.persistence.Entity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public Client save(ClientRequest clientRequest) {
        User user = userRepository.findById(clientRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Client client = new Client();
        client.setCpf(clientRequest.getCpf());
        client.setUser(user);
        
        return clientRepository.save(client);
    }

    public Client findByCpf(String cpf) {
        return clientRepository.findById(cpf).orElse(null);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client update(String cpf, ClientRequest clientRequest) {
        Client client = clientRepository.findById(cpf).orElse(null);
        if (client == null) {
            return null;
        }

        User user = userRepository.findById(clientRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        client.setUser(user);
        client.setCpf(clientRequest.getCpf());

        return clientRepository.save(client);
    }

    public boolean deleteByCpf(String cpf) {
        Client client = clientRepository.findById(cpf).orElse(null);
        if (client == null) {
            return false;
        }
        clientRepository.delete(client);
        return true;
    }
}
