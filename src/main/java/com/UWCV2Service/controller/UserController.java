package com.UWCV2Service.controller;

import com.UWCV2Service.model.Role;
import com.UWCV2Service.model.User;
import com.UWCV2Service.service.JwtService;
import com.UWCV2Service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
  private static final String BEARER_NAME = "Bearer ";
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @PostMapping(value = "/user/sign-in")
  private ResponseEntity<?> signIn(@RequestBody User user,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
      throws JSONException {
    JSONObject jsonObject = new JSONObject();
    try {

      Authentication authentication = this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getEmail(),
                                                  user.getPassword()));
      log.info("isAuthenticated: {}", authentication.isAuthenticated());
      if (authentication.isAuthenticated()) {
        UserDetails existed = (UserDetails)authentication.getPrincipal();

        List<Object> claims = existed.getAuthorities()
                                  .stream()
                                  .map(GrantedAuthority::getAuthority)
                                  .collect(Collectors.toList());
        String subject = existed.getUsername();
        String issuer = request.getRequestURL().toString();
        Long time = (1000 * 60L);
        String access_token =
            jwtService.createToken(claims, subject, issuer, time * 1);
        String refresh_token =
            jwtService.createToken(claims, subject, issuer, time * 3);

        jsonObject.put("access_token", access_token);
        jsonObject.put("refresh_token", refresh_token);
        return new ResponseEntity<Object>(jsonObject.toString(), HttpStatus.OK);
      }
    } catch (JSONException e) {
      jsonObject.put("error", e.getMessage());
      return new ResponseEntity<Object>(jsonObject.toString(),
                                        HttpStatus.UNAUTHORIZED);
    }
    return null;
  }

  @GetMapping(value = "/users")
  private ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(this.userService.getUsers());
  }

  @PostMapping(value = "/users/save")
  private ResponseEntity<?> saveUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.saveUser(user, true));
  }

  @GetMapping(value = "/user")
  private ResponseEntity<?>
  getUsersByRole(@RequestParam("roleName") String roleName) throws Exception {
    List<User> filterUsers = userService.getUsers()
                                 .stream()
                                 .filter((user) -> {
                                   List<Role> roles = user.getRoles();
                                   for (int i = 0; i < roles.size(); i++) {
                                     if (roles.get(i).getName() != roleName) {
                                       return true;
                                     }
                                   }
                                   return false;
                                 })
                                 .collect(Collectors.toList());
    return ResponseEntity.ok(filterUsers);
  }

  @GetMapping("/token/refresh")
  public ResponseEntity<?> refreshToken(HttpServletRequest request)
      throws Exception, JSONException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    Predicate<String> checkAuthHeader =
        (s) -> s != null && s.startsWith(BEARER_NAME);

    JSONObject jsonObject = new JSONObject();
    try {
      if (!checkAuthHeader.test(authorizationHeader)) {
        throw new RuntimeException("Refresh token is missing!");
      }

      String token = authorizationHeader.substring(BEARER_NAME.length());
      String userName = this.jwtService.extractSubject(token);
      User user = this.userService.getUser(userName);

      List<Object> claims = user.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toList());
      String subject = user.getName();
      String issuer = request.getRequestURL().toString();
      Long time = (long)1000 * 60;
      String access_token =
          jwtService.createToken(claims, subject, issuer, time * 1);
      String refresh_token =
          jwtService.createToken(claims, subject, issuer, time * 3);
      jsonObject.put("access_token", access_token);
      jsonObject.put("refresh_token", refresh_token);
      return ResponseEntity.ok(jsonObject.toString());

    } catch (JSONException e) {
      jsonObject.put("error", e.getMessage());
      return new ResponseEntity<Object>(jsonObject.toString(),
                                        HttpStatus.UNAUTHORIZED);
    }
  }

  @PostMapping(value = "/role/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
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
