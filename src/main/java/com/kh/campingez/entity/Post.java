package com.kh.campingez.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post{
  private int pnumber;
  private String nickname;
  private String ptitle;
  private String ptext;
  private char ptype;
  private String udate;
}