package com.helpduck.helpducksolutioncenter.entity;

import lombok.Data;

@Data
public class Comment {
  User ownerComment;
  String comment;
}
