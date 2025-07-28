package com.procter.procter_app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tests")
@Data
public class Test {
    @Id
    private String id;
    private String title;
    private List<Question> questions;
    private String creatorId;    // Teacher's id
    private String joinCode;     // Random code for students

}
