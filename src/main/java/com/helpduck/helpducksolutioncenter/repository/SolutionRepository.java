package com.helpduck.helpducksolutioncenter.repository;

import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.Solution;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SolutionRepository extends MongoRepository<Solution, String> {
  @Query("{'ticketId': ?0}")
  Optional<Solution> findByTicketId(String id);
}