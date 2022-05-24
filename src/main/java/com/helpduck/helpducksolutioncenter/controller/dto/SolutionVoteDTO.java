package com.helpduck.helpducksolutioncenter.controller.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class SolutionVoteDTO {

  @Id
  private String solutionId;
  private boolean upVote;
  private boolean downVote;

  public SolutionVoteDTO(String solutionId, boolean upVote, boolean downVote) {
    this.solutionId = solutionId;
    this.upVote = upVote;
    this.downVote = downVote;
  }

}
