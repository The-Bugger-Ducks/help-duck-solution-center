package com.helpduck.helpducksolutioncenter.repository;

import com.helpduck.helpducksolutioncenter.entity.Problem;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRepository extends MongoRepository<Problem, String> {
}