package com.syntaxsquad.centro_treinamento.model.mensalidade;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class MensalidadeService {

    @Autowired
    private MensalidadeRepository mensalidadeRepository;

    @Autowired
    private UserRepository userRepository;

    // Método agendado para verificar mensalidades atrasadas a cada 24 horas
    @Scheduled(fixedRate = 86400000) // 24 horas em milissegundos (86400000 ms)
    public void verificarMensalidadesVencidas() {
        LocalDate dataAtual = LocalDate.now();

        // Buscar todas as mensalidades pendentes
        List<Mensalidade> mensalidades = mensalidadeRepository.findByStatusMensalidade("PENDENTE");

        for (Mensalidade mensalidade : mensalidades) {
            // Verificar se a mensalidade está vencida
            if (mensalidade.getDataVencimento().isBefore(dataAtual)) {
                // Atualizar o status da mensalidade
                mensalidade.setStatusMensalidade("VENCIDA");

                // Salvar a mensalidade com o novo status
                mensalidadeRepository.save(mensalidade);

                System.out.println("Mensalidade " + mensalidade.getId() + " foi marcada como vencida.");
            }
        }
    }

    // Método para criar uma nova mensalidade
    public MensalidadeResponse createMensalidade(MensalidadeRequest mensalidadeRequest) {
        User user = userRepository.findActiveUserByCpf(mensalidadeRequest.getAluno_cpf())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

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
                .collect(Collectors.toList());
    }

    // Método para buscar todas as mensalidades
    public List<MensalidadeResponse> getAllMensalidades() {
        return mensalidadeRepository.findAll()
                .stream()
                .map(this::toResponse) // Converte todas as mensalidades para resposta
                .collect(Collectors.toList());
    }

    // Método para atualizar uma mensalidade existente
    public MensalidadeResponse updateMensalidade(String cpf, MensalidadeRequest request) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByUserCpf(cpf);
        if (mensalidades.isEmpty()) {
            throw new RuntimeException("Nenhuma mensalidade encontrada para o CPF fornecido.");
        }

        Mensalidade mensalidade = mensalidades.get(0);
        mensalidade.setDataVencimento(request.getDataVencimento());
        mensalidade.setDataPagamento(request.getDataPagamento());
        mensalidade.setValorMensalidade(request.getValorMensalidade());
        mensalidade.setStatusMensalidade(request.getStatusMensalidade());

        Mensalidade updatedMensalidade = mensalidadeRepository.save(mensalidade);
        return toResponse(updatedMensalidade); // Retorna a resposta convertida
    }

    // Método para deletar uma mensalidade
    public void deleteMensalidade(String cpf) {
        List<Mensalidade> mensalidades = mensalidadeRepository.findByUserCpf(cpf);
        if (mensalidades.isEmpty()) {
            throw new RuntimeException("Nenhuma mensalidade encontrada para o CPF fornecido.");
        }

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

    // Método para iniciar a verificação de mensalidades logo após a aplicação iniciar
    @PostConstruct
    public void iniciarVerificacaoMensalidades() {
        verificarMensalidadesVencidas();  // Chama a verificação logo ao iniciar a aplicação
    }
}
