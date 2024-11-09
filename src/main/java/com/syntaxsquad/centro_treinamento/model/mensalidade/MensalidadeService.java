package com.syntaxsquad.centro_treinamento.model.mensalidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntaxsquad.centro_treinamento.enums.Role;
import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private UserRepository userRepository;

    // Método para criar uma nova mensalidade
    public MensalidadeResponse createMensalidade(MensalidadeRequest mensalidadeRequest) {
        // Busca do usuário
        User user = userRepository.findActiveUserByCpf(mensalidadeRequest.getAluno_cpf())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        System.out.println(user.getRole().toString());
        System.out.println("User role: " + user.getRole());
        // Verifica se o usuário tem o papel de "ROLE_STUDENT"
        if (!user.getRole().toString().equals("STUDENT")) {
            throw new IllegalArgumentException("User is not a student");
        }
    
        // Verifica se a data de vencimento é anterior à data atual
        if (mensalidadeRequest.getDataVencimento().isBefore(mensalidadeRequest.getDataPagamento())) {
            throw new IllegalArgumentException("Payment date cannot be before the due date");
        }
    
        // Criação da mensalidade
        Mensalidade mensalidade = new Mensalidade();
        mensalidade.setUser(user); 
        mensalidade.setDataVencimento(mensalidadeRequest.getDataVencimento());
        mensalidade.setDataPagamento(mensalidadeRequest.getDataPagamento());
        mensalidade.setValorMensalidade(mensalidadeRequest.getValorMensalidade());
        mensalidade.setStatusMensalidade(mensalidadeRequest.getStatusMensalidade());
    
        // Salva a mensalidade no banco
        mensalidade = mensalidadeRepository.save(mensalidade);
    
        // Retorna a resposta formatada
        MensalidadeResponse response = new MensalidadeResponse();
        response.setId(mensalidade.getId());
        response.setAluno_cpf(user.getCpf());
        response.setDataVencimento(mensalidade.getDataVencimento());
        response.setDataPagamento(mensalidade.getDataPagamento());
        response.setValorMensalidade(mensalidade.getValorMensalidade());
        response.setStatusMensalidade(mensalidade.getStatusMensalidade());
    
        return response;
    }
    // Método para buscar todas as mensalidades de um CPF específico
    public List<MensalidadeResponse> getMensalidadeByCpf(String cpf) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByUserCpf(cpf);
        
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
        List<Mensalidade> mensalidades = mensalidadeRepository.findByUserCpf(cpf);
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
        List<Mensalidade> mensalidades = mensalidadeRepository.findByUserCpf(cpf);
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
        response.setAluno_cpf(mensalidade.getUser().getCpf()); // Adiciona o CPF do cliente
        response.setDataVencimento(mensalidade.getDataVencimento());
        response.setDataPagamento(mensalidade.getDataPagamento());
        response.setValorMensalidade(mensalidade.getValorMensalidade());
        response.setStatusMensalidade(mensalidade.getStatusMensalidade());
        return response;
    }
}
