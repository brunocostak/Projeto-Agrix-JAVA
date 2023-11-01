package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.dto.ResponsePersonDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**.
 * PersonController class.
 */
@RestController
public class PersonController {

  private final PersonRepository personRepositorie;

  @Autowired
  public PersonController(PersonRepository personRespositorie) {
    this.personRepositorie = personRespositorie;
  }

  /**
   * create.
   */
  @PostMapping("/persons")
  public ResponseEntity<ResponsePersonDto> create(@RequestBody PersonDto personDto) {
    Person newPerson = personRepositorie.save(personDto.toPerson());
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePersonDto(
        newPerson.getId(),
        newPerson.getUsername(),
        newPerson.getRole()));
  }
}
