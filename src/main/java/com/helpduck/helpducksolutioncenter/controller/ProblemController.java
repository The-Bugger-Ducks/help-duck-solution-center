package com.helpduck.helpducksolutioncenter.controller;

import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.Problem;
import com.helpduck.helpducksolutioncenter.entity.Solution;
import com.helpduck.helpducksolutioncenter.model.hateoas.ProblemHateoas;
import com.helpduck.helpducksolutioncenter.model.problem.ProblemLinkAdder;
import com.helpduck.helpducksolutioncenter.model.problem.ProblemUpdater;
import com.helpduck.helpducksolutioncenter.repository.ProblemRepository;
import com.helpduck.helpducksolutioncenter.service.ProblemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/problems")
public class ProblemController {

  @Autowired
  private ProblemRepository repository;
  @Autowired
  private ProblemService service;
  @Autowired
  ProblemLinkAdder linkAdder;

  @GetMapping("/")
  public ResponseEntity<Page<ProblemHateoas>> getProblems(Pageable pageable) {

    ResponseEntity<Page<ProblemHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Page<ProblemHateoas> pageProblemHateoas = service.findAll(pageable);
    if (!pageProblemHateoas.isEmpty()) {
      linkAdder.addLink(pageProblemHateoas);
      response = new ResponseEntity<Page<ProblemHateoas>>(pageProblemHateoas, HttpStatus.FOUND);
    }
    return response;
  }

  @GetMapping("/{problemId}")
  public ResponseEntity<ProblemHateoas> getProblem(@PathVariable String problemId) {

    ResponseEntity<ProblemHateoas> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    ProblemHateoas problemHateoas = service.findByIdHateoas(problemId);
    if (problemHateoas != null) {
      linkAdder.addLink(problemHateoas);
      return new ResponseEntity<ProblemHateoas>(problemHateoas, HttpStatus.FOUND);
    }
    return response;
  }

  @PostMapping("/create")
  public ResponseEntity<Problem> createSolution(@RequestBody Problem problem) {

    if (problem.getId() != null) {
      return new ResponseEntity<Problem>(HttpStatus.CONFLICT);
    }

    Problem problemInserted = service.create(problem);
    return new ResponseEntity<Problem>(problemInserted, HttpStatus.CREATED);
  }

  @PutMapping("/update/{problemId}")
  public ResponseEntity<HttpStatus> updateProblem(@PathVariable String problemId, @RequestBody Solution newSolution) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Optional<Problem> problemOptional = repository.findById(problemId);

    if (!problemOptional.isEmpty()) {
      Problem problem = problemOptional.get();
      ProblemUpdater updater = new ProblemUpdater();
      updater.incrementSolutionList(problem, newSolution);
      repository.save(problem);
      status = HttpStatus.OK;
    }
    return new ResponseEntity<HttpStatus>(status);
  }
}
