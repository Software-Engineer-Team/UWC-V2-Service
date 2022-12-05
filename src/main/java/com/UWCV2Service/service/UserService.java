package com.UWCV2Service.service;

import com.UWCV2Service.model.Role;
import com.UWCV2Service.model.User;
import java.util.List;

/**
 * UserService
 */
public interface UserService {
  User saveUser(User user, String roleName) throws Exception;

  User findUserByEmail(String email);

  User findUserByName(String name);

  User getUser(String email) throws Exception;

  List<User> getUsers();

  User addRoleToUser(String email, String roleName) throws Exception;

  Role saveRole(Role role);
}
