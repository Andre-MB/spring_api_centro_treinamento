package com.syntaxsquad.centro_treinamento.model.user.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Criar um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody EnderecoRequest enderecoRequest) {
        // Aqui estamos recebendo o CPF do aluno do corpo da requisição
        String alunoCpf = enderecoRequest.getAluno_cpf(); // Supondo que o CPF do aluno é enviado no corpo da requisição
        Endereco endereco = enderecoService.save(alunoCpf, enderecoRequest); // Chama o serviço para salvar o endereço
        return new ResponseEntity<>(endereco, HttpStatus.CREATED); // Retorna o endereço criado com status 201
    }

    // Atualizar um endereço com base no CPF
    @PutMapping("/{cpf}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable("cpf") String cpf, 
                                                   @RequestBody EnderecoRequest enderecoRequest) {
        // O "cpf" é o parâmetro passado na URL, que será usado para buscar e atualizar o endereço
        Endereco endereco = enderecoService.update(cpf, enderecoRequest); // Chama o serviço para atualizar o endereço
        if (endereco == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o endereço não for encontrado
        }
        return new ResponseEntity<>(endereco, HttpStatus.OK); // Retorna o endereço atualizado com status 200
    }

    // Buscar um endereço com base no CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable("cpf") String cpf) {
        Endereco endereco = enderecoService.findByCpf(cpf); // Chama o serviço para buscar o endereço pelo CPF
        if (endereco == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o endereço não for encontrado
        }
        return new ResponseEntity<>(endereco, HttpStatus.OK); // Retorna o endereço encontrado com status 200
    }

    // Excluir um endereço com base no CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable("cpf") String cpf) {
        boolean deleted = enderecoService.delete(cpf); // Chama o serviço para excluir o endereço pelo CPF
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o endereço não for encontrado
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 se o endereço for excluído com sucesso
    }
}
