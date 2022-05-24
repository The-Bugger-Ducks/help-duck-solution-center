package com.helpduck.helpducksolutioncenter.controller;

import java.util.Optional;

import com.helpduck.helpducksolutioncenter.controller.dto.SolutionVoteDTO;
import com.helpduck.helpducksolutioncenter.entity.SolutionComment;
import com.helpduck.helpducksolutioncenter.model.solutionComment.SolutionUpdater;
import com.helpduck.helpducksolutioncenter.repository.SolutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solutions")
public class SolutionCommentVotingController {
  @Autowired
  private SolutionRepository repository;

  @PostMapping("/vote")
  public ResponseEntity<HttpStatus> updateSolution(@RequestBody SolutionVoteDTO solutionVote) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    Optional<SolutionComment> solutionOptional = repository.findById(solutionVote.getSolutionId());

    if (!solutionOptional.isEmpty()) {
      SolutionComment solution = solutionOptional.get();
      SolutionUpdater updater = new SolutionUpdater();

      if (solutionVote.isUpVote()) {
        updater.incrementUpVote(solution);
      } else {
        updater.incrementDownVote(solution);
      }
      repository.save(solution);
      status = HttpStatus.OK;
    }
    return new ResponseEntity<HttpStatus>(status);
  }
}
