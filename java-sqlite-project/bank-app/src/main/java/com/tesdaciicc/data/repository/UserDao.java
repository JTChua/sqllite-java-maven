package com.tesdaciicc.data.repository;

import com.tes.bankapp.model.User;
import com.tesdaciicc.data.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

  public void insert(User user) {
    String sql = "INSERT INTO users(firstName, lastName, userName, password) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, user.getFirstName());
      stmt.setString(2, user.getLastName());
      stmt.setString(3, user.getUserName());
      stmt.setString(4, user.getPassword());
      stmt.executeUpdate();
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("UserDao.insert", e, null);
    }
  }

  public User getById(Long id) {
    String sql = "SELECT * FROM users WHERE userId = ?";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return mapResultToUser(rs);
      }
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("UserDao.getById", e, null);
    }
    return null;
  }

  public User getByUserName(String userName) {
    String sql = "SELECT * FROM users WHERE userName = ?";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, userName);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return mapResultToUser(rs);
      }
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("UserDao.getByUserName", e, null);
    }
    return null;
  }

  public List<User> getAll() {
    String sql = "SELECT * FROM users";
    List<User> users = new ArrayList<>();
    try (Connection conn = DatabaseUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        users.add(mapResultToUser(rs));
      }
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("UserDao.getAll", e, null);
    }
    return users;
  }

  public void update(User user) {
    String sql = "UPDATE users SET firstName = ?, lastName = ?, userName = ?, password = ?, lastLogin = ? WHERE userId = ?";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, user.getFirstName());
      stmt.setString(2, user.getLastName());
      stmt.setString(3, user.getUserName());
      stmt.setString(4, user.getPassword());
      stmt.setTimestamp(5, user.getLastLogin() != null ? Timestamp.valueOf(user.getLastLogin()) : null);
      stmt.setLong(6, user.getUserId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("UserDao.update", e, null);
    }
  }

  public void delete(Long userId) {
    String sql = "DELETE FROM users WHERE userId = ?";
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setLong(1, userId);
      stmt.executeUpdate();
    } catch (SQLException e) {
      DatabaseUtil.handleSqlException("UserDao.delete", e, null);
    }
  }

  private User mapResultToUser(ResultSet rs) throws SQLException {
    User user = new User();
    user.setUserId(rs.getLong("userId"));
    user.setFirstName(rs.getString("firstName"));
    user.setLastName(rs.getString("lastName"));
    user.setUserName(rs.getString("userName"));
    user.setPassword(rs.getString("password"));
    user.setCreatedDate(rs.getTimestamp("createdDate").toLocalDateTime());
    Timestamp lastLogin = rs.getTimestamp("lastLogin");
    if (lastLogin != null) {
      user.setLastLogin(lastLogin.toLocalDateTime());
    }
    return user;
  }

}