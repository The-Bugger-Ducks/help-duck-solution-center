package com.helpduck.helpducksolutioncenter.model.hateoas;

import java.time.LocalDateTime;

import com.helpduck.helpducksolutioncenter.entity.Solution;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolutionHateoas extends RepresentationModel<SolutionHateoas> {

  private String id;
  private String problemId;
  private String title;
  private String description;
  private LocalDateTime createdAt;

  public SolutionHateoas(Solution solution) {
    this.id = solution.getId();
    this.problemId = solution.getProblemId();
    this.title = solution.getTitle();
    this.description = solution.getDescription();
    this.createdAt = solution.getCreatedAt();
  }
}