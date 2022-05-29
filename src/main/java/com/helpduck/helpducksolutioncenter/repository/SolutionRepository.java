package com.helpduck.helpducksolutioncenter.repository;

import com.helpduck.helpducksolutioncenter.entity.Solution;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SolutionRepository extends MongoRepository<Solution, String> {
  @Query("{ 'title':{ $regex:?0, '$options' : 'i' }}")
  Page<Solution> findAllBySolutionTitle(Pageable page, String titleOrDescription);
}