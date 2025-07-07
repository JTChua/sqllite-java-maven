package com.tesdaciicc.data.repository;

import com.tes.bankapp.model.Transaction;
import com.tesdaciicc.data.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

  public void insert(Transaction txn) {
    String sql = "INSERT INTO transactions(accountId, amount, transactionType, referenceNo) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, txn.getAccountId());
      stmt.setBigDecimal(2, txn.getAmount());
      stmt.setString(3, txn.getTransactionType());
      stmt.setString(4, txn.getReferenceNo());
      stmt.executeUpdate();
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("TransactionDao.insert", e, null);
    }
  }

  public Transaction getById(Long id) {
    String sql = "SELECT * FROM transactions WHERE transactionId = ?";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return mapResultToTransaction(rs);
      }
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("TransactionDao.getById", e, null);
    }
    return null;
  }

  public List<Transaction> getAll() {
    String sql = "SELECT * FROM transactions";
    List<Transaction> txns = new ArrayList<>();
    try (Connection conn = DatabaseUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        txns.add(mapResultToTransaction(rs));
      }
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("TransactionDao.getAll", e, null);
    }
    return txns;
  }

  public void update(Transaction txn) {
    String sql = "UPDATE transactions SET accountId = ?, amount = ?, transactionType = ?, referenceNo = ?, transactionDate = ? WHERE transactionId = ?";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, txn.getAccountId());
      stmt.setBigDecimal(2, txn.getAmount());
      stmt.setString(3, txn.getTransactionType());
      stmt.setString(4, txn.getReferenceNo());
      stmt.setTimestamp(5, Timestamp.valueOf(txn.getTransactionDate()));
      stmt.setLong(6, txn.getTransactionId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("TransactionDao.update", e, null);
    }
  }

  public void delete(Long transactionId) {
    String sql = "DELETE FROM transactions WHERE transactionId = ?";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setLong(1, transactionId);
      stmt.executeUpdate();
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("TransactionDao.delete", e, null);
    }
  }

  private Transaction mapResultToTransaction(ResultSet rs) throws SQLException {
    Transaction txn = new Transaction();
    txn.setTransactionId(rs.getLong("transactionId"));
    txn.setAccountId(rs.getInt("accountId"));
    txn.setAmount(rs.getBigDecimal("amount"));
    txn.setTransactionType(rs.getString("transactionType"));
    txn.setTransactionDate(rs.getTimestamp("transactionDate").toLocalDateTime());
    txn.setReferenceNo(rs.getString("referenceNo"));
    return txn;
  }

}
