package com.tesdaciicc.data.repository;

import com.tesdaciicc.data.model.Account;
import com.tesdaciicc.data.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    public void insert(Account account) {
        String sql = "INSERT INTO accounts(userId, accountNo, accountType, balance, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, account.getUserId());
            stmt.setString(2, account.getAccountNo());
            stmt.setString(3, account.getAccountType());
            stmt.setBigDecimal(4, account.getBalance());
            stmt.setString(5, account.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            DatabaseUtil.handleSqlException("AccountDao.insert", e, null);
        }
    }

    public Account getById(Long id) {
        String sql = "SELECT * FROM accounts WHERE accountId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultToAccount(rs);
            }
        } catch (SQLException e) {
            DatabaseUtil.handleSqlException("AccountDao.getById", e, null);
        }
        return null;
    }

    public List<Account> getAll() {
        String sql = "SELECT * FROM accounts";
        List<Account> accounts = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                accounts.add(mapResultToAccount(rs));
            }
        } catch (SQLException e) {
            DatabaseUtil.handleSqlException("AccountDao.getAll", e, null);
        }
        return accounts;
    }

    public void update(Account account) {
        String sql = "UPDATE accounts SET userId = ?, accountNo = ?, accountType = ?, balance = ?, status = ?, createdDate = ? WHERE accountId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, account.getUserId());
            stmt.setString(2, account.getAccountNo());
            stmt.setString(3, account.getAccountType());
            stmt.setBigDecimal(4, account.getBalance());
            stmt.setString(5, account.getStatus());
            stmt.setTimestamp(6, Timestamp.valueOf(account.getCreatedDate()));
            stmt.setLong(7, account.getAccountId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            DatabaseUtil.handleSqlException("AccountDao.update", e, null);
        }
    }

    public void delete(Long accountId) {
        String sql = "DELETE FROM accounts WHERE accountId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            DatabaseUtil.handleSqlException("AccountDao.delete", e, null);
        }
    }

    private Account mapResultToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getLong("accountId"));
        account.setUserId(rs.getInt("userId"));
        account.setAccountNo(rs.getString("accountNo"));
        account.setAccountType(rs.getString("accountType"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime());
        account.setStatus(rs.getString("status"));
        return account;
    }

}
