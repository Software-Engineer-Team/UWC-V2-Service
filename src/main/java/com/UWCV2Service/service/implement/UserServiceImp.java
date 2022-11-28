package com.UWCV2Service.service.implement;

import com.UWCV2Service.model.Role;
import com.UWCV2Service.model.User;
import com.UWCV2Service.repository.RoleRepository;
import com.UWCV2Service.repository.UserRepository;
import com.UWCV2Service.service.UserService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserServiceImp
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService, UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;
  private final RoleRepository roleRepository;

  @Override
  public User saveUser(User user, boolean encryptPass) {
    if (encryptPass) {
      user.setPassword(this.encoder.encode(user.getPassword()));
    }
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
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {

    log.info("email: {}", email);
    User user = userRepository.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException("User is not found!"));
    log.info("email: {}", user.getEmail());

    Collection<GrantedAuthority> authorities =
        user.getRoles()
            .stream()
            .map((role) -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    log.info("password: {}", user.getPassword());

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(), authorities);
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public User addRoleToUser(String email, String roleName) throws Exception {
    log.info("email: {}", email);
    log.info("roleName: {}", roleName);
    User user = this.userRepository.findByEmail(email).orElseThrow(
        () -> new Exception(String.format("User %s is not found!", email)));
    Role role = this.roleRepository.findByName(roleName).orElseThrow(
        () -> new Exception(String.format("Role %s is not found!", roleName)));
    user.getRoles().add(role);
    log.info("Adding role {} to user {} successfully", roleName, email);
    return user;
  }

  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role {} to the database successfully", role.getName());
    return this.roleRepository.save(role);
  }
}
