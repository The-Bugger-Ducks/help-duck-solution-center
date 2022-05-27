package com.helpduck.helpducksolutioncenter.repository;

import java.util.List;
import com.helpduck.helpducksolutioncenter.entity.Problem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProblemRepository extends MongoRepository<Problem, String> {
  @Query("{ 'solutionList':{ $elemMatch: { 'title' : { $regex:/^?0/i }}}}")
  List<Problem> findAllBySolutionTitle(String titleOrDescription);
}