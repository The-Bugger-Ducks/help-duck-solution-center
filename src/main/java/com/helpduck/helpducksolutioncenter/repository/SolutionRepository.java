package com.helpduck.helpducksolutioncenter.repository;

import com.helpduck.helpducksolutioncenter.entity.Solution;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SolutionRepository extends MongoRepository<Solution, String> {

}