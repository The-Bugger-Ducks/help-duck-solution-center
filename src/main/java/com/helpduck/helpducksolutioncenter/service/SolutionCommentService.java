package com.helpduck.helpducksolutioncenter.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.helpduck.helpducksolutioncenter.entity.SolutionComment;
import com.helpduck.helpducksolutioncenter.model.hateoas.SolutionCommentHateoas;
import com.helpduck.helpducksolutioncenter.repository.SolutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolutionCommentService {

  @Autowired
  private SolutionRepository repository;

  @Transactional(readOnly = true)
  public Page<SolutionCommentHateoas> findAll(Pageable pageable) {
    Page<SolutionComment> solution = repository.findAll(pageable);
    Page<SolutionCommentHateoas> page = solution.map(x -> new SolutionCommentHateoas(x));
    return page;
  }

  @Transactional(readOnly = true)
  public SolutionComment findById(String id) {
    Optional<SolutionComment> solutionOptional = repository.findById(id);
    if (solutionOptional.isEmpty()) {
      return null;
    }
    SolutionComment solution = solutionOptional.get();
    return solution;
  }

  @Transactional(readOnly = true)
  public SolutionCommentHateoas findByIdHateoas(String id) {
    Optional<SolutionComment> solution = repository.findById(id);
    if (solution.isEmpty()) {
      return null;
    }
    SolutionCommentHateoas SolutionHateoas = new SolutionCommentHateoas(solution.get());
    return SolutionHateoas;
  }

  @Transactional(readOnly = true)
  public SolutionCommentHateoas findByIdTicketHateoas(String id) {
    Optional<SolutionComment> solution = repository.findByTicketId(id);
    if (solution.isEmpty()) {
      return null;
    }
    SolutionCommentHateoas SolutionHateoas = new SolutionCommentHateoas(solution.get());
    return SolutionHateoas;
  }

  public SolutionComment create(SolutionComment solution) {
    solution.setUpVote(0);
    solution.setDownVote(0);
    solution.setCreatedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    SolutionComment solutionInserted = repository.insert(solution);
    return solutionInserted;
  }
}
