package com.procter.procter_app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password; // Hashed
    private String role;     // TEACHER or STUDENT

}
