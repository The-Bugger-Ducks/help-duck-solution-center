package com.helpduck.helpducksolutioncenter.controller;

import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.SolutionComment;
import com.helpduck.helpducksolutioncenter.model.hateoas.SolutionCommentHateoas;
import com.helpduck.helpducksolutioncenter.model.solutionComment.SolutionLinkAdder;
import com.helpduck.helpducksolutioncenter.model.solutionComment.SolutionUpdater;
import com.helpduck.helpducksolutioncenter.repository.SolutionRepository;
import com.helpduck.helpducksolutioncenter.service.SolutionCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solutions")
public class SolutionCommentController {
  @Autowired
  private SolutionRepository repository;
  @Autowired
  private SolutionCommentService service;
  @Autowired
  SolutionLinkAdder linkAdder;

  @GetMapping("/")
  public ResponseEntity<Page<SolutionCommentHateoas>> getSolutions(Pageable pageable) {

    ResponseEntity<Page<SolutionCommentHateoas>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    Page<SolutionCommentHateoas> pageSolutionHateoas = service.findAll(pageable);
    if (!pageSolutionHateoas.isEmpty()) {
      linkAdder.addLink(pageSolutionHateoas);
      response = new ResponseEntity<Page<SolutionCommentHateoas>>(pageSolutionHateoas, HttpStatus.FOUND);
    }
    return response;
  }

  @GetMapping("/{solutionId}")
  public ResponseEntity<SolutionCommentHateoas> getSolution(@PathVariable String solutionId) {

    SolutionCommentHateoas solutionHateoas = service.findByIdHateoas(solutionId);
    if (solutionHateoas == null) {
      new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    linkAdder.addLink(solutionHateoas);
    return new ResponseEntity<SolutionCommentHateoas>(solutionHateoas, HttpStatus.FOUND);
  }

  @GetMapping("/ticket/{ticketId}")
  public ResponseEntity<SolutionCommentHateoas> getSolutionByIdTicket(@PathVariable String ticketId) {

    SolutionCommentHateoas solutionHateoas = service.findByIdTicketHateoas(ticketId);
    if (solutionHateoas == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    linkAdder.addLink(solutionHateoas);
    return new ResponseEntity<SolutionCommentHateoas>(solutionHateoas, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<SolutionComment> createSolution(@RequestBody SolutionComment solution) {

    if (solution.getId() != null) {
      return new ResponseEntity<SolutionComment>(HttpStatus.CONFLICT);
    }

    SolutionComment solutionInserted = service.create(solution);
    return new ResponseEntity<SolutionComment>(solutionInserted, HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<HttpStatus> updateSolution(@RequestBody SolutionComment updatedSolution) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Optional<SolutionComment> solutionOptional = repository.findById(updatedSolution.getId());

    if (!solutionOptional.isEmpty()) {
      SolutionComment solution = solutionOptional.get();
      SolutionUpdater updater = new SolutionUpdater();
      updater.update(solution, updatedSolution);
      repository.save(solution);
      status = HttpStatus.OK;
    }
    return new ResponseEntity<HttpStatus>(status);
  }

  @DeleteMapping("/delete/{solutionId}")
  public ResponseEntity<HttpStatus> deleteSolution(@PathVariable String solutionId) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Optional<SolutionComment> solutionOptional = repository.findById(solutionId);

    if (!solutionOptional.isEmpty()) {
      repository.deleteById(solutionId);
      status = HttpStatus.OK;
    }
    return new ResponseEntity<HttpStatus>(status);
  }
}
