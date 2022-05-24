package com.helpduck.helpducksolutioncenter.model.hateoas;

import java.time.LocalDateTime;
import java.util.List;

import com.helpduck.helpducksolutioncenter.entity.Problem;
import com.helpduck.helpducksolutioncenter.entity.Solution;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemHateoas extends RepresentationModel<ProblemHateoas> {

  private String id;
  private String title;
  private List<Solution> solutionList;

  private int count;

  private LocalDateTime createdAt;

  public ProblemHateoas(Problem problem) {
    this.id = problem.getId();
    this.title = problem.getTitle();
    this.solutionList = problem.getSolutionList();
    this.count = problem.getCount();
    this.createdAt = problem.getCreatedAt();
  }
}