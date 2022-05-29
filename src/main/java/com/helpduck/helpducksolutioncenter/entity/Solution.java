package com.helpduck.helpducksolutioncenter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Solution {

  @Id
  private String id;

  private String problemId;

  private String title;
  private String description;
  private LocalDateTime createdAt;
}
