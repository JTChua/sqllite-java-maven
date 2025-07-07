package com.tesdaciicc.data.model;

import java.time.LocalDateTime; // if using Java 8+
import java.math.BigDecimal;

public class Account {

  private Long accountId; // corresponds to INTEGER PRIMARY KEY AUTOINCREMENT
  private Long userId; // INTEGER NOT NULL (FK to users table)
  private String accountNo; // TEXT NOT NULL UNIQUE
  private String accountType; // TEXT NOT NULL
  private BigDecimal balance; // REAL NOT NULL DEFAULT 0.00
  private LocalDateTime createdDate; // TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  private String status; // TEXT DEFAULT 'ACTIVE'

  // --- Getters and Setters ---

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
