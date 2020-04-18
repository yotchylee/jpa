package com.example.jpa.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@ApiModel(value = "user对象")
@Entity // This tells Hibernate to make a table out of this class
public class User {
  @ApiModelProperty(value = "主键",example="10")
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @ApiModelProperty(value = "名",example="三")
  private String name;

  @ApiModelProperty(value = "姓",example="张")
  private String lastName;

  @ApiModelProperty(value = "邮箱",example="google@gmail.com")
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}