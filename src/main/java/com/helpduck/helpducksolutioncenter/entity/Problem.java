package com.helpduck.helpducksolutioncenter.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class Problem {
  @Id
  private String id;

  private String title;

  private List<Solution> solutionList;

  private int count;

  private LocalDateTime createdAt;

}
