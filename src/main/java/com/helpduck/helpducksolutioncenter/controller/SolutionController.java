package com.helpduck.helpducksolutioncenter.controller;

import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.Solution;
import com.helpduck.helpducksolutioncenter.model.hateoas.SolutionHateoas;
import com.helpduck.helpducksolutioncenter.model.solutions.SolutionLinkAdder;
import com.helpduck.helpducksolutioncenter.repository.SolutionRepository;
import com.helpduck.helpducksolutioncenter.service.SolutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("solutions")
public class SolutionController {
  @Autowired
  private SolutionRepository repository;
  @Autowired
  private SolutionService service;
  @Autowired
  SolutionLinkAdder linkAdder;

  MongoTemplate mongoTemplate;

  @GetMapping("/")
  public ResponseEntity<Page<SolutionHateoas>> getSolutions(Pageable pageable) {

    ResponseEntity<Page<SolutionHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Page<SolutionHateoas> pageSolutionHateoas = service.findAll(pageable);
    if (!pageSolutionHateoas.isEmpty()) {
      linkAdder.addLink(pageSolutionHateoas);
      response = new ResponseEntity<Page<SolutionHateoas>>(pageSolutionHateoas, HttpStatus.FOUND);
    }
    return response;
  }

  @GetMapping("/{solutionId}")
  public ResponseEntity<SolutionHateoas> getSolution(@PathVariable String solutionId) {

    SolutionHateoas solutionHateoas = service.findByIdHateoas(solutionId);
    if (solutionHateoas == null) {
      new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    linkAdder.addLink(solutionHateoas);
    return new ResponseEntity<SolutionHateoas>(solutionHateoas, HttpStatus.FOUND);
  }

  @PostMapping("/create")
  public ResponseEntity<Solution> createSolution(@RequestBody Solution solution) {

    if (solution.getId() != null) {
      return new ResponseEntity<Solution>(HttpStatus.CONFLICT);
    }

    Solution solutionInserted = service.create(solution);
    return new ResponseEntity<Solution>(solutionInserted, HttpStatus.CREATED);
  }

  // @PutMapping("/update")
  // public ResponseEntity<HttpStatus> updateSolution(@RequestBody Solution
  // updatedSolution) {

  // HttpStatus status = HttpStatus.BAD_REQUEST;
  // Optional<Solution> solutionOptional =
  // repository.findById(updatedSolution.getId());

  // if (!solutionOptional.isEmpty()) {
  // Solution solution = solutionOptional.get();
  // SolutionUpdater updater = new SolutionUpdater();
  // updater.update(solution, updatedSolution);
  // repository.save(solution);
  // status = HttpStatus.OK;
  // }
  // return new ResponseEntity<HttpStatus>(status);
  // }

  @DeleteMapping("/delete/{solutionId}")
  public ResponseEntity<HttpStatus> deleteSolution(@PathVariable String solutionId) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Optional<Solution> solutionOptional = repository.findById(solutionId);

    if (!solutionOptional.isEmpty()) {
      repository.deleteById(solutionId);
      status = HttpStatus.OK;
    }
    return new ResponseEntity<HttpStatus>(status);
  }
}
