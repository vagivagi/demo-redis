package com.example.demo;

import java.io.Serializable;

public class UserForm implements Serializable {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
