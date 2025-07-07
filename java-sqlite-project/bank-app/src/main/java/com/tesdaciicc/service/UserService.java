package com.tesdaciicc.service;

//Handles user registration, login, and profile updates.
public class UserService {

  private final UserDao userDao = new UserDao();

  // Register new user
  public User registerUser(String firstName, String lastName, String userName, String password) {
    if (userDao.getByUserName(userName) != null) {
      throw new IllegalArgumentException("Username already exists.");
    }

    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setUserName(userName);
    user.setPassword(password); // Add hashing if needed
    user.setCreatedDate(LocalDateTime.now());

    userDao.insert(user);
    return userDao.getByUserName(userName); // return user with ID assigned
  }

  // Authenticate login
  public User login(String userName, String password) {
    User user = userDao.getByUserName(userName);
    if (user != null && user.getPassword().equals(password)) {
      user.setLastLogin(LocalDateTime.now());
      userDao.update(user);
      return user;
    }
    return null; // invalid credentials
  }

}
