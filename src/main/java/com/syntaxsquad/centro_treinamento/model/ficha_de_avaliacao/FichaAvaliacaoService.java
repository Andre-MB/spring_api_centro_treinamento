package com.syntaxsquad.centro_treinamento.model.ficha_de_avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syntaxsquad.centro_treinamento.model.email.EmailService;
import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserRepository;

@Service
public class FichaAvaliacaoService {

    @Autowired
    private FichaAvaliacaoRepository fichaAvaliacaoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public FichaAvaliacaoResponse createFicha(FichaAvaliacaoRequest request) {
        FichaAvaliacao ficha = new FichaAvaliacao();
        mapRequestToEntity(request, ficha);

        // Buscar o usuário com o CPF fornecido e verificar se ele é um estudante ativo
        User user = userRepository.findActiveUserByCpf(request.getAluno_cpf())

                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getRole().toString().equals("STUDENT")) {
            throw new IllegalArgumentException("O usuário não é um estudante.");
        }

        // Associar o usuário à ficha de avaliação
        ficha.setUser(user);

        // Salvar a ficha de avaliação no banco de dados
        FichaAvaliacao savedFicha = fichaAvaliacaoRepository.save(ficha);

        // Enviar email de confirmação
        emailService.sendEmail(user.getEmail(), "Ficha de Avaliação Criada",
                "Olá, " + user.getName() + "! Sua ficha de avaliação foi criada com sucesso.");

        // Retornar a resposta com a ficha salva
        return new FichaAvaliacaoResponse(savedFicha);
    }

    @Transactional
    public FichaAvaliacaoResponse updateFicha(Long id, FichaAvaliacaoRequest request) {
        // Buscar a ficha existente pelo ID
        FichaAvaliacao ficha = fichaAvaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ficha não encontrada"));

        // Atualizar os dados da ficha com base na solicitação
        mapRequestToEntity(request, ficha);

        // Salvar as alterações no banco de dados
        FichaAvaliacao updatedFicha = fichaAvaliacaoRepository.save(ficha);

        // Retornar a resposta com a ficha atualizada
        return new FichaAvaliacaoResponse(updatedFicha);
    }

    // Novo método para buscar ficha de avaliação por CPF
    public FichaAvaliacaoResponse getFichaByCpf(String cpf) {
        // Buscar o usuário pelo CPF
        User user = userRepository.findActiveUserByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Buscar a ficha de avaliação associada ao usuário
        FichaAvaliacao ficha = fichaAvaliacaoRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Ficha de avaliação não encontrada para este usuário"));

        // Retornar a resposta com a ficha encontrada
        return new FichaAvaliacaoResponse(ficha);
    }

    // Método auxiliar para mapear dados do request para a entidade FichaAvaliacao
    private void mapRequestToEntity(FichaAvaliacaoRequest request, FichaAvaliacao ficha) {
        ficha.setAltura(request.getAltura());
        ficha.setPeso(request.getPeso());
        ficha.setProfissao(request.getProfissao());
        ficha.setConvivioSocial(request.isConvivioSocial());
        ficha.setCondicionamentoFisico(request.isCondicionamentoFisico());
        ficha.setNecessidadeMedica(request.isNecessidadeMedica());
        ficha.setOutrosObjetivos(request.getOutrosObjetivos());
        ficha.setProblemaCardiaco(request.isProblemaCardiaco());
        ficha.setDorPeito(request.isDorPeito());
        ficha.setTontura(request.isTontura());
        ficha.setProblemaOsseo(request.isProblemaOsseo());
        ficha.setMedicacao(request.isMedicacao());
        ficha.setQuaisMedicamentos(request.getQuaisMedicamentos());
        ficha.setCirurgia(request.isCirurgia());
        ficha.setTipoCirurgia(request.getTipoCirurgia());
        ficha.setAnoCirurgia(request.getAnoCirurgia());
        ficha.setGravida(request.isGravida());
        ficha.setMesesGravidez(request.getMesesGravidez());
        ficha.setFuma(request.isFuma());
        ficha.setCigarrosPorDia(request.getCigarrosPorDia());
        ficha.setIngereBebidaAlcoolica(request.isIngereBebidaAlcoolica());
        ficha.setFrequenciaAlcool(request.getFrequenciaAlcool());
        ficha.setAtividadeFisicaAtual(request.isAtividadeFisicaAtual());
        ficha.setFrequenciaAtividade(request.getFrequenciaAtividade());
        ficha.setPerderPeso(request.isPerderPeso());
        ficha.setMelhorarAptidaoCardiovascular(request.isMelhorarAptidaoCardiovascular());
        ficha.setMelhorarFlexibilidade(request.isMelhorarFlexibilidade());
        ficha.setMelhorarCondicionamentoFisico(request.isMelhorarCondicionamentoFisico());
        ficha.setReduzirDoresCostas(request.isReduzirDoresCostas());
        ficha.setReduzirEstresse(request.isReduzirEstresse());
        ficha.setDiminuirColesterol(request.isDiminuirColesterol());
        ficha.setCoordenacaoMotora(request.isCoordenacaoMotora());
        ficha.setEspecificoObjetivo(request.getEspecificoObjetivo());
        ficha.setSexo(request.getSexo());
        ficha.setBracoDireito(request.getBracoDireito());
        ficha.setBracoEsquerdo(request.getBracoEsquerdo());
        ficha.setCoxaDireita(request.getCoxaDireita());
        ficha.setCoxaEsquerda(request.getCoxaEsquerda());
        ficha.setPeitoralDireito(request.getPeitoralDireito());
        ficha.setPeitoralEsquerdo(request.getPeitoralEsquerdo());
        ficha.setCintura(request.getCintura());
        ficha.setTorax(request.getTorax());
        ficha.setImc(request.getImc());
        ficha.setClassificacaoImc(request.getClassificacaoImc());
        ficha.setPercentualGorduraCorporal(request.getPercentualGorduraCorporal());
        ficha.setPercentualMusculoEsqueletico(request.getPercentualMusculoEsqueletico());
        ficha.setMetabolismoRepouso(request.getMetabolismoRepouso());
        ficha.setIdadeBiologica(request.getIdadeBiologica());
        ficha.setNivelGorduraVisceral(request.getNivelGorduraVisceral());
    }

}
