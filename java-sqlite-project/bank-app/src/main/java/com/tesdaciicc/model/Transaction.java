package com.tesdaciicc.data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

  private Long transactionId; // INTEGER PRIMARY KEY AUTOINCREMENT
  private Long accountId; // INTEGER NOT NULL (FK to accounts table)
  private BigDecimal amount; // REAL NOT NULL
  private String transactionType; // TEXT NOT NULL
  private LocalDateTime transactionDate; // TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  private String referenceNo; // TEXT NOT NULL UNIQUE

  // --- Getters and Setters ---

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public LocalDateTime getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(LocalDateTime transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getReferenceNo() {
    return referenceNo;
  }

  public void setReferenceNo(String referenceNo) {
    this.referenceNo = referenceNo;
  }

}
