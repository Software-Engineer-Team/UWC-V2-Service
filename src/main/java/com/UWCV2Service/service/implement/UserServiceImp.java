package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.Role;
import com.UWCV2Service.model.User;
import com.UWCV2Service.repository.RoleRepository;
import com.UWCV2Service.repository.UserRepository;
import com.UWCV2Service.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserServiceImp
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {
  private final UserRepository userRepository;
  // private final PasswordEncoder encoder;
  private final RoleRepository roleRepository;

  @Override
  public User saveUser(User user, String roleName) throws Exception {
    Role role = roleRepository.findByName(roleName).orElseThrow(
        () -> new Exception(String.format("Role %s is not found!", roleName)));
    user.getRoles().add(role);
    return userRepository.save(user);
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }

  @Override
  public User getUser(String email) throws Exception {
    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new Exception("User is not found!"));
    return user;
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public User addRoleToUser(String email, String roleName) throws Exception {
    log.info("email: {}", email);
    log.info("roleName: {}", roleName);
    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new Exception(String.format("User %s is not found!", email)));
    Role role = roleRepository.findByName(roleName).orElseThrow(
        () -> new Exception(String.format("Role %s is not found!", roleName)));
    user.getRoles().add(role);
    log.info("Adding role {} to user {} successfully", roleName, email);
    return user;
  }

  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role {} to the database successfully", role.getName());
    return roleRepository.save(role);
  }

  @Override
  public User findUserByName(String name) {
    return userRepository.findByName(name).orElse(null);
  }
}
