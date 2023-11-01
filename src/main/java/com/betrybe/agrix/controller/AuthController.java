package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.JwtDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.services.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

  public AuthController(TokenService tokenService, PersonRepository personRepository) {
    this.tokenService = tokenService;
    this.personRepository = personRepository;
  }

  /**
   * login.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Person person) {
    Person personExist = personRepository.findByUsername(person.getUsername()).orElse(null);
    if (personExist == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    if (!personExist.getPassword().equals(person.getPassword())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    String token = tokenService.generateToken(personExist);
    return ResponseEntity.ok().body(new JwtDto(token));
  }

}
