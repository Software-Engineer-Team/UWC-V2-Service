package com.UWCV2Service.controller;

import com.UWCV2Service.model.Role;
import com.UWCV2Service.model.User;
import com.UWCV2Service.service.UserService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * UserController
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
  private final UserService userService;

  @GetMapping(value = "/users")
  private ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(this.userService.getUsers());
  }

  @PostMapping(value = "/user/save")
  private ResponseEntity<?> saveUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.saveUser(user, true));
  }

  @GetMapping(value = "/users-role")
  private ResponseEntity<?>
  getUsersByRole(@RequestParam("roleName") String roleName) throws Exception {
    List<User> filterUsers =
        userService.getUsers()
            .stream()
            .filter((user) -> {
              List<Role> roles = user.getRoles();
              for (int i = 0; i < roles.size(); i++) {
                if (roles.get(i).getName().equals(roleName)) {
                  return true;
                }
              }
              return false;
            })
            .collect(Collectors.toList());
    return ResponseEntity.ok(filterUsers);
  }

  @PostMapping(value = "/user/sign-in-google")
  private ResponseEntity<?> signIn(@RequestBody String email) throws Exception {
    User exitedUser = userService.findUserByEmail(email);
    if (exitedUser == null) {
      throw new Exception("User not found!!!");
    };
    return ResponseEntity.ok().body(exitedUser);
  }

  @PostMapping(value = "/user/sign-in")
  private ResponseEntity<?> signIn(@RequestBody User user) throws Exception {
    User exitedUser = userService.findUserByEmail(user.getEmail());
    if (exitedUser == null) {
      throw new Exception("User not found!!!");
    };
    if (!exitedUser.getPassword().equals(user.getPassword())) {
      throw new Exception("Password doesn't match!!!");
    }
    return ResponseEntity.ok().body(exitedUser);
  }

  @PostMapping(value = "/role/save")
  private ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                             .path("/api/role/save")
                             .toUriString());
    return ResponseEntity.created(uri).body(this.userService.saveRole(role));
  }

  @PostMapping(value = "/role/addRoleToUser")
  public ResponseEntity<User>
  addRoleToUser(@RequestBody RoleUserForm roleUserForm) throws Exception {
    log.info(roleUserForm.toString());
    User user = this.userService.addRoleToUser(roleUserForm.getEmail(),
                                               roleUserForm.getRoleName());
    return ResponseEntity.ok().body(user);
  }

  @Data
  /** RoletoUserForm */
  static final class RoleUserForm {
    private String email;
    private String roleName;

    @Override
    public String toString() {
      return "RoleUserForm [email=" + email + ", roleName=" + roleName + "]";
    }
  }
}
