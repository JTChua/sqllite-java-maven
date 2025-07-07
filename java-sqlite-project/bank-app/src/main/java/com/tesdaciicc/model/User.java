package com.tesdaciicc.model;

import java.time.LocalDateTime;

public class User {

  private Long userId; // INTEGER PRIMARY KEY AUTOINCREMENT
  private String firstName; // TEXT NOT NULL
  private String lastName; // TEXT NOT NULL
  private String userName; // TEXT NOT NULL UNIQUE
  private String password; // TEXT NOT NULL
  private LocalDateTime createdDate; // TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  private LocalDateTime lastLogin; // TIMESTAMP, nullable

  // --- Getters and Setters ---

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

}
