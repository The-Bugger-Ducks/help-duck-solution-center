package com.helpduck.helpducksolutioncenter.model.hateoas;

import java.time.LocalDateTime;
import java.util.List;

import com.helpduck.helpducksolutioncenter.entity.Comment;
import com.helpduck.helpducksolutioncenter.entity.SolutionComment;

import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolutionCommentHateoas extends RepresentationModel<SolutionCommentHateoas> {

  @Id
  private String id;
  private String ticketId;
  private String titleProblem;
  private List<String> problemTags;

  private Comment solutionComment;

  private int upVote;
  private int downVote;

  private LocalDateTime createdAt;

  public SolutionCommentHateoas(SolutionComment solution) {
    this.id = solution.getId();
    this.ticketId = solution.getTicketId();
    this.problemTags = solution.getProblemTags();
    this.titleProblem = solution.getTitleProblem();

    this.solutionComment = solution.getSolutionComment();

    this.upVote = solution.getUpVote();
    this.downVote = solution.getDownVote();

    this.createdAt = solution.getCreatedAt();
  }
}