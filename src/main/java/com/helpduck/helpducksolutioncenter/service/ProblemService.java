package com.helpduck.helpducksolutioncenter.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.Problem;
import com.helpduck.helpducksolutioncenter.model.hateoas.ProblemHateoas;
import com.helpduck.helpducksolutioncenter.repository.ProblemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProblemService {

  @Autowired
  private ProblemRepository repository;

  @Transactional(readOnly = true)
  public Page<ProblemHateoas> findAll(Pageable pageable) {

    Page<Problem> problem = repository.findAll(pageable);
    Page<ProblemHateoas> page = problem.map(x -> new ProblemHateoas(x));

    return page;
  }

  @Transactional(readOnly = true)
  public com.helpduck.helpducksolutioncenter.entity.Problem findById(String id) {

    Optional<Problem> problemOptional = repository.findById(id);
    if (problemOptional.isEmpty()) {
      return null;
    }
    Problem problem = problemOptional.get();

    return problem;
  }

  @Transactional(readOnly = true)
  public ProblemHateoas findByIdHateoas(String id) {

    Optional<Problem> problem = repository.findById(id);
    if (problem.isEmpty()) {
      return null;
    }
    ProblemHateoas problemHateoas = new ProblemHateoas(problem.get());

    return problemHateoas;
  }

  public Problem create(Problem problem) {

    problem.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    problem.setCount(0);
    Problem problemInserted = repository.insert(problem);

    return problemInserted;
  }
}
