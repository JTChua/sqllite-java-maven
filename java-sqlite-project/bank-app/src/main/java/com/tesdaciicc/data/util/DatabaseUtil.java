package com.tesdaciicc.data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseUtil {
  // private static final String URL = "jdbc:sqlite:banking-app.db";
  private static final String URL;
  private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());
  private static final String EXCEPTION_FORMAT = "Exception in %s, message: %s, code: %s";

  // alternative to URL = "jdbc:sqlite:banking-app.db"; for absolute path and full
  // flexibility
  static {
    String currentDir = System.getProperty("user.dir"); // the directory JVM started from
    URL = "jdbc:sqlite:" + currentDir + "/bank-app.db";
  }

  private static Connection connection;

  public static Connection getConnection() {
    if (connection == null) {
      synchronized (DatabaseUtil.class) {
        if (connection == null) {
          try {
            connection = DriverManager.getConnection(URL);
            enableForeignKeys(connection);
          } catch (SQLException e) {
            handleSqlException("DatabaseUtil.getConnection", e, LOGGER);
          }
        }
      }
    }
    return connection;
  }

  private static void enableForeignKeys(Connection conn) {
    try (Statement stmt = conn.createStatement()) {
      stmt.execute("PRAGMA foreign_keys = ON");
    } catch (SQLException e) {
      handleSqlException("DatabaseUtil.enableForeignKeys", e, LOGGER);
    }
  }

  public static void handleSqlException(String method, SQLException e, Logger log) {
    if (e != null && log != null) {
      log.warning(String.format(EXCEPTION_FORMAT, method, e.getMessage(), e.getErrorCode()));
      throw new RuntimeException(e);
    }
  }

  // private static final String URL = "jdbc:sqlite:banking-app.db";
  // private static final Logger LOGGER =
  // Logger.getLogger(DatabaseUtils.class.getName());
  // private static final String exceptionFormat = "exception in %s, message: %s,
  // code: %s";
  // private static Connection connection;

  // public static Connection getConnection() {
  // if (connection == null) {
  // synchronized (DatabaseUtils.class) {
  // if (connection == null) {
  // try {
  // connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
  // } catch (SQLException e) {
  // handleSqlException("DatabaseUtils.getConnection", e, LOGGER);
  // }
  // }
  // }
  // }
  // return connection; // Return the singleton connection instance
  // }

  // public static void handleSqlException(String method, SQLException e, Logger
  // log) {
  // if (e == null || log == null)
  // return;
  // if (e.getErrorCode() == 0) {
  // log.warning(String.format("exception in %s, message: %s, code: %s", method,
  // e.getMessage(), e.getErrorCode()));
  // }
  // log.warning(String.format(exceptionFormat, method, e.getMessage(),
  // e.getErrorCode()));
  // throw new RuntimeException(e);
  // }
}
