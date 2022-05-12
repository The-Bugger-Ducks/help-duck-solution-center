package com.helpduck.helpducksolutioncenter.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class Solution {

  @Id
  private String id;
  private String ticketId;
  private String titleProblem;

  // tags related to issues that the solution is valid for
  private List<String> problemTags;

  private Comment solutionComment;

  private int upVote;
  private int downVote;

  private LocalDateTime createdAt;
}