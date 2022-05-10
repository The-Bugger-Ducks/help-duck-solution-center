package com.helpduck.helpducksolutioncenter.model.hateoas;

import java.time.LocalDateTime;
import java.util.List;

import com.helpduck.helpducksolutioncenter.entity.Comment;
import com.helpduck.helpducksolutioncenter.entity.Solution;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolutionHateoas extends RepresentationModel<SolutionHateoas> {

  @Id
  private String id;
  private String idTicket;
  private String titleProblem;
  private List<String> problemTags;

  private Comment solutionComment;

  private int upVote;
  private int downVote;

  private LocalDateTime createdAt;

  public SolutionHateoas(Solution solution) {
    this.id = solution.getId();
    this.idTicket = solution.getIdTicket();
    this.problemTags = solution.getProblemTags();
    this.titleProblem = solution.getTitleProblem();

    this.solutionComment = solution.getSolutionComment();

    this.upVote = solution.getDownVote();
    this.downVote = solution.getDownVote();

    this.createdAt = solution.getCreatedAt();
  }
}