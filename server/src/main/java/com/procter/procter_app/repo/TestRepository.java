package com.procter.procter_app.repo;

import com.procter.procter_app.model.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends MongoRepository<Test, String> {
    Optional<Test> findByJoinCode(String code);
    List<Test> findByCreatorId(String creatorId);
}

