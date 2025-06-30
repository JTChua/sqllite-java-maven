package com.tesdaciicc.data.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BankService {

  private UUID serviceId; // Auto-generated UUID
  private String serviceName; // e.g., "Wire Transfer"
  private String description; // Service details
  private BigDecimal fee; // Use BigDecimal for precision
  private boolean isActive; // Active/inactive flag
  private LocalDateTime createdAt; // Creation timestamp
  private LocalDateTime updatedAt; // Last modified timestamp
  private BigDecimal minAmount; // Optional: Minimum transaction amount
  private BigDecimal maxAmount; // Optional: Maximum transaction amount
  private String currency; // "Php"

  // no argument constructor
  public BankService() {
    this.serviceId = UUID.randomUUID(); // Auto-generate UUID
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public BankService(String serviceName, BigDecimal fee, String currency) {
    this();
    this.serviceName = serviceName;
    this.fee = fee;
    this.currency = currency;
    this.isActive = true;
  }

  public UUID getServiceId() {
    return serviceId;
  }

  public void setServiceId(UUID serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public BigDecimal getMinAmount() {
    return minAmount;
  }

  public void setMinAmount(BigDecimal minAmount) {
    this.minAmount = minAmount;
  }

  public BigDecimal getMaxAmount() {
    return maxAmount;
  }

  public void setMaxAmount(BigDecimal maxAmount) {
    this.maxAmount = maxAmount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public boolean isFreeService() {
    return fee.compareTo(BigDecimal.ZERO) == 0;
  }

  public boolean isAmountInRange(BigDecimal amount) {
    if (minAmount == null || maxAmount == null)
      return true;
    return amount.compareTo(minAmount) >= 0 && amount.compareTo(maxAmount) <= 0;
  }

  @Override
  public String toString() {
    return "BankService [serviceId=" + serviceId
        + ", serviceName=" + serviceName
        + ", description=" + description
        + ", isActive=" + isActive
        + ", createdAt=" + createdAt
        + ", updatedAt=" + updatedAt
        + "]";
  }

}
