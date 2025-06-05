package com.personal.project.estante_critica_api.initializer;

import com.personal.project.estante_critica_api.endpoints.dto.user.NewUserDTO;
import com.personal.project.estante_critica_api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final String adminPassword;
    private final String emailAdmin;

    public DataInitializer(UserService service,
                           @Value("${admin.app.password}") String adminPassword,
                           @Value("${email.admin.app}") String emailAdmin) {
        this.userService = service;
        this.adminPassword = adminPassword;
        this.emailAdmin = emailAdmin;
    }

    @Override
    public void run(String... args) throws Exception {
        var userDefault = "adminApp";
        // Verifica se já existe um usuário com username "admin"
        if (Boolean.FALSE.equals(userService.existUserByUsername(userDefault))) {
            // Recupera a senha definida na variável de ambiente ADMIN_PASSWORD
            if (adminPassword == null || adminPassword.isEmpty()) {
                throw new IllegalStateException("ADMIN_APP_PASSWORD não está definido!");
            }

            if (emailAdmin == null || emailAdmin.isEmpty()) {
                throw new IllegalStateException("EMAIL_ADMIN_APP não está definido!");
            }
            // Cria o usuário admin com a senha encriptada
            var user = new NewUserDTO(userDefault, userDefault, emailAdmin, adminPassword);
            userService.registerUserDefault(user, Boolean.TRUE);

            log.info("Usuário admin criado com sucesso.");
        } else {
            log.warn("Usuário admin já existe.");
        }
    }
}
