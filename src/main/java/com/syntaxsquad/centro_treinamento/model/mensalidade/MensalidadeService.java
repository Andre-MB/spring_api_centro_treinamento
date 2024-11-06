package com.syntaxsquad.centro_treinamento.model.mensalidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntaxsquad.centro_treinamento.model.alunos.Alunos;
import com.syntaxsquad.centro_treinamento.model.alunos.AlunoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private AlunoRepository clientRepository;

    // Método para criar uma nova mensalidade
    public MensalidadeResponse createMensalidade(MensalidadeRequest request) {
        // Verifica se o cliente existe pelo CPF
        Optional<Alunos> clientOpt = clientRepository.findByCpf(request.getClientId());
        if (clientOpt.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado para o CPF fornecido.");
        }

        // Criação e preenchimento da nova mensalidade
        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setClient(clientOpt.get());
        mensalidade.setDataVencimento(request.getDataVencimento());
        mensalidade.setDataPagamento(request.getDataPagamento());
        mensalidade.setValorMensalidade(request.getValorMensalidade());
        mensalidade.setStatusMensalidade(request.getStatusMensalidade());

        // Salva a mensalidade no banco de dados
        Mensalidade savedMensalidade = mensalidadeRepository.save(mensalidade);
        return toResponse(savedMensalidade); // Retorna a resposta convertida
    }

    // Método para buscar todas as mensalidades de um CPF específico
    public List<MensalidadeResponse> getMensalidadeByCpf(String cpf) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByClientCpf(cpf);
        
        if (mensalidades == null || mensalidades.isEmpty()) {
            throw new RuntimeException("Nenhuma mensalidade encontrada para o CPF fornecido.");
        }
        
        return mensalidades.stream()
                           .map(this::toResponse) // Converte cada mensalidade para resposta
                           .collect(Collectors.toList()); // Retorna como lista
    }

    // Método para buscar todas as mensalidades
    public List<MensalidadeResponse> getAllMensalidades() {
        return mensalidadeRepository.findAll()
                .stream()
                .map(this::toResponse) // Converte todas as mensalidades para resposta
                .collect(Collectors.toList()); // Retorna como lista
    }

    // Método para atualizar uma mensalidade existente
    public MensalidadeResponse updateMensalidade(String cpf, MensalidadeRequest request) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByClientCpf(cpf);
        if (mensalidades.isEmpty()) {
            throw new RuntimeException("Nenhuma mensalidade encontrada para o CPF fornecido.");
        }

        // Atualiza a primeira mensalidade encontrada
        Mensalidade mensalidade = mensalidades.get(0); 
        mensalidade.setDataVencimento(request.getDataVencimento());
        mensalidade.setDataPagamento(request.getDataPagamento());
        mensalidade.setValorMensalidade(request.getValorMensalidade());
        mensalidade.setStatusMensalidade(request.getStatusMensalidade());

        // Salva a mensalidade atualizada
        Mensalidade updatedMensalidade = mensalidadeRepository.save(mensalidade);
        return toResponse(updatedMensalidade); // Retorna a resposta convertida
    }

    // Método para deletar uma mensalidade
    public void deleteMensalidade(String cpf) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByClientCpf(cpf);
        if (mensalidades.isEmpty()) {
            throw new RuntimeException("Nenhuma mensalidade encontrada para o CPF fornecido.");
        }

        // Deleta a primeira mensalidade encontrada
        mensalidadeRepository.delete(mensalidades.get(0));
    }

    // Método para converter uma entidade Mensalidade para MensalidadeResponse
    private MensalidadeResponse toResponse(Mensalidade mensalidade) {
        MensalidadeResponse response = new MensalidadeResponse();
        response.setId(mensalidade.getId());
        response.setClientId(mensalidade.getClient().getCpf()); // Adiciona o CPF do cliente
        response.setDataVencimento(mensalidade.getDataVencimento());
        response.setDataPagamento(mensalidade.getDataPagamento());
        response.setValorMensalidade(mensalidade.getValorMensalidade());
        response.setStatusMensalidade(mensalidade.getStatusMensalidade());
        return response;
    }
}
