package com.tesdaciicc.data.dao;

import com.tesdaciicc.data.entity.BankService;
import com.tesdaciicc.data.util.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class AccountDao implements DataAccessObject<BankService, UUID> {

  // Logger for logging SQL exceptions and other messages
  private static final Logger LOGGER = Logger.getLogger(BankServiceDao.class.getName());

  // SQL query to retrieve all services
  private static final String GET_ALL = "select service_id, name, name, description fee ... from schema.services";

  @Override
  public BankService create(BankService entity) {
    // TODO Auto-generated method stub
    return null;
  }

  public Optional<BankService> getOne(UUID id) {
    // TODO Auto-generated method stub
    return null;
  }

  public List<BankService> getAll() {

    List<BankService> services = new ArrayList<>();
    Connection connection = DatabaseUtils.getConnection();

    try (Statement statement = connection.createStatement()) {
      ResultSet rs = statement.executeQuery(GET_ALL);
      services = this.processResultSet(rs);
    } catch (SQLException e) {
      DatabaseUtils.handleSqlException("BankServiceDao.getAll", e, LOGGER);
    }
    return services;
  }

  public BankService update(BankService service) {
    // TODO Auto-generated method stub
    return null;
  }

  public void delete(UUID id) {
    // TODO Auto-generated method stub
  }

  private List<BankService> processResultSet(ResultSet rs) throws SQLException {
    List<BankService> services = new ArrayList<>();

    // This method processes the ResultSet from a database query and converts it
    // into a list of Service objects.
    while (rs.next()) {
      Service service = new Service();
      service.setServiceId((UUID) rs.getObject("service_id"));
      service.setServiceName(rs.getString("name"));
      service.setDescription(rs.getString("description"));
      service.setFee(rs.getBigDecimal("fee"));
      service.isActive(rs.getBoolean("isActive"));
      service.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));
      service.setUpdateddAt(LocalDateTime.parse(rs.getString("updatedAt")));
      service.minAmount(rs.getBigDecimal("minAmount"));
      service.maxAmount(rs.getBigDecimal("maxAmount"));
      service.setCurrency(rs.getString("currency"));
      services.add(service);
    }
    return services;
  }

  // private BankService mapResultSetToBankService(ResultSet rs) throws
  // SQLException {
  // return new BankService(
  // UUID.fromString(rs.getString("serviceId")),
  // rs.getString("serviceName"),
  // rs.getString("description"),
  // rs.getBigDecimal("fee"),
  // rs.getBoolean("isActive"),
  // LocalDateTime.parse(rs.getString("createdAt")),
  // LocalDateTime.parse(rs.getString("updatedAt")),
  // rs.getBigDecimal("minAmount"),
  // rs.getBigDecimal("maxAmount"),
  // rs.getString("currency")
  // );

}
