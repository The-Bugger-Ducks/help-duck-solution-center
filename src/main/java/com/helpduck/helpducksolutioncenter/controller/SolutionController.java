package com.helpduck.helpducksolutioncenter.controller;

import com.helpduck.helpducksolutioncenter.entity.Solution;
import com.helpduck.helpducksolutioncenter.model.hateoas.SolutionHateoas;
import com.helpduck.helpducksolutioncenter.model.solution.SolutionLinkAdder;
import com.helpduck.helpducksolutioncenter.repository.SolutionRepository;
import com.helpduck.helpducksolutioncenter.service.SolutionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solution")
public class SolutionController {

  @Autowired
  private SolutionRepository repository;
  @Autowired
  private SolutionService service;
  @Autowired
  SolutionLinkAdder linkAdder;

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

    ResponseEntity<SolutionHateoas> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    SolutionHateoas solutionHateoas = service.findByIdHateoas(solutionId);
    if (solutionHateoas != null) {
      linkAdder.addLink(solutionHateoas);
      return new ResponseEntity<SolutionHateoas>(solutionHateoas, HttpStatus.FOUND);
    }
    return response;
  }

  @GetMapping("/search/{searchTitle}")
  public ResponseEntity<Page<Solution>> getAllSolutionByTitle(@PathVariable String searchTitle, Pageable page) {

    ResponseEntity<Page<Solution>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Page<Solution> solutionOptional = repository.findAllBySolutionTitle(page, searchTitle);
    if (solutionOptional.getContent().size() > 0) {
      return new ResponseEntity<Page<Solution>>(solutionOptional, HttpStatus.FOUND);
    }
    return response;
  }

  @PostMapping("/create")
  public ResponseEntity<Solution> createSolution(@RequestBody Solution solution) {

    if (solution.getId() != null) {
      return new ResponseEntity<Solution>(HttpStatus.CONFLICT);
    }

    Solution solutionInserted = service.create(solution);
    return new ResponseEntity<Solution>(solutionInserted, HttpStatus.CREATED);
  }
}
