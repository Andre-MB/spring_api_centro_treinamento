package com.syntaxsquad.centro_treinamento.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) {
        validateCpf(admin.getCpf());
        return adminRepository.save(admin);
    }

    public Admin getAdminById(String cpf) {
        return adminRepository.findById(cpf)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found with CPF: " + cpf));
    }

    public void deleteAdmin(String cpf) {
        if (!adminRepository.existsById(cpf)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found with CPF: " + cpf);
        }
        adminRepository.deleteById(cpf);
    }

    private void validateCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
        int digito1 = calcularDigitoVerificador(cpf, 9);
        int digito2 = calcularDigitoVerificador(cpf, 10);
        if (digito1 != Character.getNumericValue(cpf.charAt(9)) ||
            digito2 != Character.getNumericValue(cpf.charAt(10))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF inválido");
        }
    }

    private int calcularDigitoVerificador(String cpf, int peso) {
        int soma = 0;
        for (int i = 0; i < peso; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (peso + 1 - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
    public Admin getAdminByCpf(String cpf) {
        return adminRepository.findByCpf(cpf)
            .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
    }
    
}
