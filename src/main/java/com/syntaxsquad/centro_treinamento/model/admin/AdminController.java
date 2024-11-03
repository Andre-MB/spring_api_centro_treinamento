package com.syntaxsquad.centro_treinamento.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.syntaxsquad.centro_treinamento.model.user.User;
import com.syntaxsquad.centro_treinamento.model.user.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
       
        // Criar objeto Admin
        Admin admin = new Admin();
        admin.setCpf(adminRequest.getCpf());
        

        // Buscar o usuário correspondente pelo ID
        User user = userService.findUserById(adminRequest.getUserId()); // Buscando usuário pelo userId
        admin.setUser(user); // Atribuindo o usuário ao administrador

        // Salvar o administrador
        Admin savedAdmin = adminService.createAdmin(admin);

        // Converter o objeto Admin para AdminResponse
        return new AdminResponse(
            savedAdmin.getCpf(),
            savedAdmin.getUser().getId()
        );
    }

    @GetMapping("/{cpf}")
    public AdminResponse getAdminByCpf(@PathVariable String cpf) {
        Admin admin = adminService.getAdminByCpf(cpf);
        return new AdminResponse(
            admin.getCpf(),
            admin.getUser().getId()
        );
    }

    @GetMapping("/all")
    public List<AdminResponse> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return admins.stream()
                .map(admin -> new AdminResponse(
                    admin.getCpf(),
                    admin.getUser().getId()
                ))
                .toList();
    }
    @DeleteMapping("/{cpf}")
    public void deleteAdmin(@PathVariable String cpf) {
        adminService.deleteAdmin(cpf);
    }
    @PutMapping
    public AdminResponse updateAdmin(@RequestBody AdminRequest adminRequest) {
        Admin admin = new Admin();
        admin.setCpf(adminRequest.getCpf());
        admin.setUser(userService.findUserById(adminRequest.getUserId()));
        Admin updatedAdmin = adminService.createAdmin(admin);
        return new AdminResponse(
            updatedAdmin.getCpf(),
            updatedAdmin.getUser().getId()
        );
    }
}
