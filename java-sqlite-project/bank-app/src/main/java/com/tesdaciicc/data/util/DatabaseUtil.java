package com.tesdaciicc.data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseUtil {
  private static final String URL = "jdbc:sqlite:banking-app.db";
  private static final Logger LOGGER = Logger.getLogger(DatabaseUtils.class.getName());
  private static final String exceptionFormat = "exception in %s, message: %s, code: %s";
  private static Connection connection;

  public static Connection getConnection() {
    if (connection == null) {
      synchronized (DatabaseUtils.class) {
        if (connection == null) {
          try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
          } catch (SQLException e) {
            handleSqlException("DatabaseUtils.getConnection", e, LOGGER);
          }
        }
      }
    }
    return connection; // Return the singleton connection instance
  }

  public static void handleSqlException(String method, SQLException e, Logger log) {
    if (e == null || log == null)
      return;
    if (e.getErrorCode() == 0) {
      log.warning(String.format("exception in %s, message: %s, code: %s", method, e.getMessage(), e.getErrorCode()));
    }
    log.warning(String.format(exceptionFormat, method, e.getMessage(), e.getErrorCode()));
    throw new RuntimeException(e);
  }
}
