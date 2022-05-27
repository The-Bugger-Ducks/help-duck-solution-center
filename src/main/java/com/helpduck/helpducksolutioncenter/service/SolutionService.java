package com.helpduck.helpducksolutioncenter.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.Problem;
import com.helpduck.helpducksolutioncenter.entity.Solution;
import com.helpduck.helpducksolutioncenter.model.hateoas.SolutionHateoas;
import com.helpduck.helpducksolutioncenter.model.problem.ProblemUpdater;
import com.helpduck.helpducksolutioncenter.repository.ProblemRepository;
import com.helpduck.helpducksolutioncenter.repository.SolutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolutionService {

  @Autowired
  private SolutionRepository repository;
  @Autowired
  private ProblemRepository problemRepository;

  ProblemUpdater updater = new ProblemUpdater();

  @Transactional(readOnly = true)
  public Page<SolutionHateoas> findAll(Pageable pageable) {

    Page<Solution> solution = repository.findAll(pageable);
    Page<SolutionHateoas> page = solution.map(x -> new SolutionHateoas(x));

    return page;
  }

  @Transactional(readOnly = true)
  public Solution findById(String id) {

    Optional<Solution> solutionOptional = repository.findById(id);
    if (solutionOptional.isEmpty()) {
      return null;
    }
    Solution solution = solutionOptional.get();

    return solution;
  }

  @Transactional(readOnly = true)
  public SolutionHateoas findByIdHateoas(String id) {

    Optional<Solution> solution = repository.findById(id);
    if (solution.isEmpty()) {
      return null;
    }
    SolutionHateoas solutionHateoas = new SolutionHateoas(solution.get());

    return solutionHateoas;
  }

  public Solution create(Solution solution) {

    Optional<Problem> problemOptional = problemRepository.findById(solution.getProblemId());
    if (problemOptional.isEmpty()) {
      return null;
    }

    Problem problem = problemOptional.get();
    updater.incrementSolutionList(problem, solution);
    problemRepository.save(problem);

    solution.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    Solution solutionInserted = repository.insert(solution);

    return solutionInserted;
  }
}
