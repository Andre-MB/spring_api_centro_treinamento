

package com.syntaxsquad.centro_treinamento.model.admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.syntaxsquad.centro_treinamento.model.admin.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByCpf(String cpf);
}
