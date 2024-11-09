package com.syntaxsquad.centro_treinamento.model.user.endereco;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UserRepository userRepository;

    // Criar ou salvar um endereço com base no CPF
    public Endereco save(String cpf, EnderecoRequest enderecoRequest) {
        // Buscar o usuário pelo CPF
        User user = userRepository.findActiveUserByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Criar o endereço
        Endereco endereco = new Endereco();
        endereco.setCep(enderecoRequest.getCep());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setPais(enderecoRequest.getPais());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setUser(user); // Associar o usuário ao endereço

        return enderecoRepository.save(endereco); // Salvar o endereço
    }

    // Buscar um endereço com base no CPF
    public Endereco findByCpf(String cpf) {
        return enderecoRepository.findByUserCpf(cpf); // Buscar por CPF
    }

    // Atualizar o endereço de um usuário com base no CPF
    public Endereco update(String cpf, EnderecoRequest enderecoRequest) {
        // Buscar o endereço pelo CPF do usuário
        Endereco endereco = enderecoRepository.findByUserCpf(cpf);

        if (endereco == null) {
            return null; // Se o endereço não for encontrado, retorna null
        }

        // Buscar o usuário ativo com base no CPF
        User user = userRepository.findActiveUserByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Atualizar os dados do endereço
        endereco.setCep(enderecoRequest.getCep());
        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setPais(enderecoRequest.getPais());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setUser(user); // Associar o novo usuário

        return enderecoRepository.save(endereco); // Salvar as alterações
    }

    // Excluir um endereço com base no CPF
    public boolean delete(String cpf) {
        // Buscar o endereço pelo CPF do usuário
        Endereco endereco = enderecoRepository.findByUserCpf(cpf);

        if (endereco == null) {
            return false; // Endereço não encontrado
        }

        enderecoRepository.delete(endereco); // Excluir o endereço
        return true; // Endereço excluído com sucesso
    }

    // Buscar todos os endereços
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }
}
