package com.syntaxsquad.centro_treinamento.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserService;


import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService; // Injetar o UserService

    @Autowired
    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping
    public AdminResponse createAdmin(@Valid @RequestBody AdminRequest adminRequest) {
        // Converter a String de birthDate para LocalDate
        LocalDate birthDate = LocalDate.parse(adminRequest.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Criar objeto Admin
        Admin admin = new Admin();
        admin.setCpf(adminRequest.getCpf());
        admin.setName(adminRequest.getName());
        admin.setBirthDate(birthDate);

        // Buscar o usuário correspondente pelo ID
        User user = userService.findUserById(adminRequest.getUserId()); // Buscando usuário pelo userId
        admin.setUser(user); // Atribuindo o usuário ao administrador

        // Salvar o administrador
        Admin savedAdmin = adminService.createAdmin(admin);

        return new AdminResponse(
            savedAdmin.getCpf(),
            savedAdmin.getName(),
            savedAdmin.getBirthDate(),
            savedAdmin.getUser().getId()
        );
    }

    @GetMapping("/{cpf}")
    public AdminResponse getAdminByCpf(@PathVariable String cpf) {
        Admin admin = adminService.getAdminByCpf(cpf);
        return new AdminResponse(
            admin.getCpf(),
            admin.getName(),
            admin.getBirthDate(),
            admin.getUser().getId()
        );
    }


}
