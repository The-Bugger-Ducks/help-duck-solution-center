package com.helpduck.helpducksolutioncenter.repository;

import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.SolutionComment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SolutionRepository extends MongoRepository<SolutionComment, String> {
  @Query("{'ticketId': ?0}")
  Optional<SolutionComment> findByTicketId(String id);
}