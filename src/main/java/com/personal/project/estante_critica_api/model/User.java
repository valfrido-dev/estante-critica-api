package com.personal.project.estante_critica_api.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private final String password;

    private Boolean isAdmin;
    private List<String> books;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = this.encryptPassword(password);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
