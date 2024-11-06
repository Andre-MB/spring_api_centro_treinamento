package com.syntaxsquad.centro_treinamento.model.treino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    // Criar um novo treino
    public TreinoResponse createTreino(TreinoRequest request) {
        Treino treino = new Treino();
        treino.setNomeTreino(request.getNomeTreino());
        treino.setDescricaoTreino(request.getDescricaoTreino());

        // Salva o treino no banco
        Treino savedTreino = treinoRepository.save(treino);

        // Converte para resposta
        return toResponse(savedTreino);
    }

    // Buscar todos os treinos
    public List<TreinoResponse> getAllTreinos() {
        List<Treino> treinos = treinoRepository.findAll();
        return treinos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    // Buscar treino por ID
    public TreinoResponse getTreinoById(UUID id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);
        if (treinoOpt.isPresent()) {
            return toResponse(treinoOpt.get());
        }
        throw new RuntimeException("Treino não encontrado");
    }

    // Atualizar treino
    public TreinoResponse updateTreino(UUID id, TreinoRequest request) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);
        if (treinoOpt.isPresent()) {
            Treino treino = treinoOpt.get();
            treino.setNomeTreino(request.getNomeTreino());
            treino.setDescricaoTreino(request.getDescricaoTreino());
            
            // Salva a atualização
            Treino updatedTreino = treinoRepository.save(treino);
            return toResponse(updatedTreino);
        }
        throw new RuntimeException("Treino não encontrado");
    }

    // Deletar treino
    public void deleteTreino(UUID id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);
        if (treinoOpt.isPresent()) {
            treinoRepository.delete(treinoOpt.get());
        } else {
            throw new RuntimeException("Treino não encontrado");
        }
    }

    // Converter para resposta
    private TreinoResponse toResponse(Treino treino) {
        TreinoResponse response = new TreinoResponse();
        response.setId(treino.getId());
        response.setNomeTreino(treino.getNomeTreino());
        response.setDescricaoTreino(treino.getDescricaoTreino());
        return response;
    }
}
