package com.syntaxsquad.centro_treinamento.model.alunos;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;

import jakarta.persistence.Entity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public Alunos save(AlunoRequest clientRequest) {
        User user = userRepository.findById(clientRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Alunos client = new Alunos();
        client.setCpf(clientRequest.getCpf());
        client.setUser(user);
        
        return clientRepository.save(client);
    }

    public Alunos findByCpf(String cpf) {
        return clientRepository.findById(cpf).orElse(null);
    }

    public List<Alunos> findAll() {
        return clientRepository.findAll();
    }

    public Alunos update(String cpf, AlunoRequest clientRequest) {
        Alunos client = clientRepository.findById(cpf).orElse(null);
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
        Alunos client = clientRepository.findById(cpf).orElse(null);
        if (client == null) {
            return false;
        }
        clientRepository.delete(client);
        return true;
    }
}
