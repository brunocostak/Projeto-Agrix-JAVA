package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.AuthDto;
import com.betrybe.agrix.dto.JwtDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.services.TokenService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController class.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final TokenService tokenService;
  private final PersonRepository personRepository;
  private final AuthenticationManager authenticationManager;

  /**.
   * AuthController constructor.
   */
  public AuthController(TokenService tokenService, PersonRepository personRepository,
      AuthenticationManager authenticationManager) {
    this.tokenService = tokenService;
    this.personRepository = personRepository;
    this.authenticationManager = authenticationManager;
  }

  /**
   * login.
   */
  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(
      @RequestBody AuthDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
            authenticationDto.username(),
            authenticationDto.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);
    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);
    Map<String, String> response = new HashMap<>();
    response.put("token", token);

    return ResponseEntity.ok(response);
  }
}
