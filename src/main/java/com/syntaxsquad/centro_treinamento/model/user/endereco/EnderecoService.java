package com.syntaxsquad.centro_treinamento.model.user.endereco;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private com.syntaxsquad.centro_treinamento.model.user.UserRepository userRepository;

    public Endereco save(EnderecoRequest enderecoRequest) {
        User user = userRepository.findById(enderecoRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Endereco endereco = new Endereco();
        endereco.setId(UUID.randomUUID());
        endereco.setCep(enderecoRequest.getCep());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setPais(enderecoRequest.getPais());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setUser(user);

        return enderecoRepository.save(endereco);
    }

    public Endereco findById(UUID id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco update(UUID id, EnderecoRequest enderecoRequest) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (endereco == null) {
            return null;
        }

        User user = userRepository.findById(enderecoRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        endereco.setCep(enderecoRequest.getCep());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setPais(enderecoRequest.getPais());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setUser(user);

        return enderecoRepository.save(endereco);
    }

    public boolean deleteById(UUID id) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (endereco == null) {
            return false;
        }
        enderecoRepository.delete(endereco);
        return true;
    }
}
